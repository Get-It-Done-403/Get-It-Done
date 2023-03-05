package com.cse403.getitdone.task;

import com.cse403.getitdone.utils.CalendarEntry;
import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TaskService {

    public static final String COL_NAME="users";
    public static final String SUBCOL_NAME="tasks";
    public static final String SUBSUBCOL_NAME="entries";

    public static String saveTaskDetails(final String uid, final Task task) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(task.getTid())
                .set(task);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Task getTaskDetails(final String uid, final String tid) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(tid);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Task task = null;

        if (document.exists()) {
            task = document.toObject(Task.class);
            return task;
        }else {
            return null;
        }
    }

    public static List<CalendarEntry> getTaskCalendarEntries(final String uid, final String tid)
            throws InterruptedException, ExecutionException {
        List<CalendarEntry> entries = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference documentReference = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(tid)
                .collection(SUBSUBCOL_NAME);
        ApiFuture<QuerySnapshot> future = documentReference.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            CalendarEntry entry = null;

            if (document.exists()) {
                entry = document.toObject(CalendarEntry.class);
                entries.add(entry);
            }
        }
        return entries;
    }

    public static String deleteTask(final String uid, final String tid) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(tid)
                .delete();

        CollectionReference collectionReference = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(tid)
                .collection(SUBSUBCOL_NAME);

        deleteCollection(collectionReference, 10);
        return "uid " + uid + ": task with tid " + tid + " has been deleted";
    }

    public static void deleteCollection(CollectionReference collection, int batchSize) {
        try {
            ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
            int deleted = 0;
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                ++deleted;
            }
            if (deleted >= batchSize) {
                deleteCollection(collection, batchSize);
            }
        } catch (Exception e) {
            System.err.println("Error deleting collection : " + e.getMessage());
        }
    }

    public List<Task> getTaskList(String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference collectionReference = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME);

        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot collection = future.get();
        List<QueryDocumentSnapshot> documents = collection.getDocuments();

        List<Task> tasks = new ArrayList<>();

        if (documents.isEmpty()) return tasks;

        documents.forEach(queryDocumentSnapshot -> {
            if (queryDocumentSnapshot.exists()) {
                tasks.add(queryDocumentSnapshot.toObject(Task.class));
            }
        });
        return tasks;
    }
}



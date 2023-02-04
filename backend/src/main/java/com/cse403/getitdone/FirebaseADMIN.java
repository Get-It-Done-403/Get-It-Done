package com.cse403.getitdone;

import ch.qos.logback.core.net.ObjectWriter;
import com.cse403.getitdone.task.Task;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseADMIN {
    private Firestore db;
    FirebaseADMIN(Firestore db) {this.db = db;}

    public FirebaseADMIN() throws IOException, ExecutionException, InterruptedException {
        FileInputStream serviceAcc = new FileInputStream("/Users/aidanpetta/IdeaProjects/Get-It-Done/backend/src/main/java/com/cse403/getitdone/servicekey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAcc))
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();
        this.db = db;
    }

    Firestore getDb() {return db;}

    void addTaskToUser(String toAdd) throws ExecutionException, InterruptedException, IOException {
        Task tester = new Task("go to class", 2023, 2, 3, 12, 30);
        Map<String, Task> map = new HashMap<>();
        map.put("uid", tester);
        ApiFuture<WriteResult> future = db
                .collection("users")
                .document("aidan")
                .set(map);
    }

    public void addCalendarToUser() {}

    public void deleteTaskToUser() {}

    //Deletes entire document from collection tasks
    public void deleteDocument(String docName) {
        ApiFuture<WriteResult> deleted = db
                .collection("users")
                .document("aidan")
                .collection("tasks")
                .document(docName)
                .delete();
    }

    public void deleteFieldFromTask() {
        DocumentReference docRef = db
                .collection("users")
                .document("aidan")
                .collection("tasks")
                .document();
        Map<String, Object> updates = new HashMap<>();
        updates.put("taskFieldToDelte", FieldValue.delete());
        ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }

    public void deleteCollection(CollectionReference collection, int batchSize) {
        try {
            ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
            int deleted = 0;
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                deleted++;
            }
            if (deleted >= batchSize) {
                deleteCollection(collection, batchSize);
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCalendar() {}

}
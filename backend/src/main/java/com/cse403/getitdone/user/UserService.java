package com.cse403.getitdone.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public static final String COL_NAME = "users";

    public static final String SUBCOL_NAME = "username";

    public static final String FIELD_NAME = "username";

    public String saveUserName(final String uid, final String username) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> future = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(FIELD_NAME)
                .set(username);
        return future.get().getUpdateTime().toString();
    }

    public String getUserName(final String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(FIELD_NAME);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toString();
        } else {
            return null;
        }
    }
}

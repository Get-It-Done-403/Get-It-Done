package com.cse403.getitdone.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public static final String COL_NAME = "users";

    public String saveUserName(final String uid, final String username) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        Map<String, String> save = new HashMap<>();
        save.put("username", username);

        ApiFuture<WriteResult> writeResultApiFuture = dbFirestore
                .collection(COL_NAME).document(uid).set(save, SetOptions.merge());

        return writeResultApiFuture.get().getUpdateTime().toString();
    }

    public String getUserName(final String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFirestore
                .collection(COL_NAME).document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if (document.exists()) {
            System.out.println(document.getData());
            return Objects.requireNonNull(document.getData()).toString();
        } else {
            return null;
        }
    }
}

package com.cse403.getitdone;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseADMIN {
    private final Firestore db;
    FirebaseADMIN(Firestore db) {this.db = db;}


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FileInputStream serviceAcc = new FileInputStream("/Users/aidanpetta/IdeaProjects/Get-It-Done/backend/src/main/java/com/cse403/getitdone/servicekey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAcc))
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> user = new HashMap<>();
        user.put("test first", "Yay");
        user.put("boof", "yayaya");

        ApiFuture<WriteResult> future = db.collection("users").document("LA").set(user);
        System.out.println("update time " + future.get().getUpdateTime());
    }

    public void addTaskToUser() {

    }

}
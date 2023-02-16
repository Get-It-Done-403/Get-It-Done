package com.cse403.getitdone;

import com.cse403.getitdone.user.UserService;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class UserServiceTests {

    public static final String COL_NAME = "users";

    public Firestore db;

    public void init() throws IOException {
        FileInputStream serciceAcc = new FileInputStream("/Users/aidanpetta/IdeaProjects/Get-It-Done/backend/src/main/resources/servicekey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serciceAcc))
                .setDatabaseUrl("https://get-it-done-7a708-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();

    }

    @Test
    public void createUserNameAndGet() throws IOException, ExecutionException, InterruptedException {
        init();

        String username = "testUserName";
        String uid = "testUSER";

        UserService instanceCreate = new UserService();

        instanceCreate.saveUserName(uid, username);

        DocumentReference docRef = db
                .collection(COL_NAME).document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        assert (document.exists());
        System.out.println(document.getData());
    }
}

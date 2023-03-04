package com.cse403.getitdone;

import com.google.api.client.json.JsonFactory;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.internal.ApiClientUtils;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FireBaseTest {
    public Firestore db;
    public void init() throws IOException {
        FileInputStream serciceAcc = new FileInputStream("../backend/src/main/resources/servicekey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serciceAcc))
                .setDatabaseUrl("https://get-it-done-7a708-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();

    }

    @Test
    public void testAdd() throws ExecutionException, InterruptedException, IOException {
        // Create a Map to store the data we want to set
        init();
        Map<String, Object> docData = new HashMap<>();
        docData.put("name", "Los Angeles");
        docData.put("state", "CA");
        docData.put("country", "USA");
        docData.put("regions", Arrays.asList("west_coast", "socal"));
        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(docData);
        // ...
        // future.get() blocks on response
        System.out.println("Update time : " + future.get().getUpdateTime());

        // try to get the data
        DocumentReference docRef = db.collection("cities").document("LA");
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> past = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = past.get();
        assert document.exists();
        System.out.println("test working");
    }

    @Test
    public void testDelete() throws IOException, ExecutionException, InterruptedException {
        // Create a Map to store the data we want to set
        init();
        Map<String, Object> docData = new HashMap<>();
        docData.put("name", "Los Angeles");
        docData.put("state", "CA");
        docData.put("country", "USA");
        docData.put("regions", Arrays.asList("west_coast", "socal"));
        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(docData);
        // ...
        // future.get() blocks on response
        System.out.println("Update time : " + future.get().getUpdateTime());


        DocumentReference del = db.collection("cities").document("LA");
        Map<String, Object> up = new HashMap<>();
        up.put("cities", FieldValue.delete());
        ApiFuture<WriteResult> writeResult = del.update(up);
        System.out.println("Update time : " + writeResult.get());

        DocumentReference delPas = db.collection("cities").document("LA");
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> pas = delPas.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot doc = pas.get();
        assert !doc.exists();
    }
}

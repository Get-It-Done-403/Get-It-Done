package com.cse403.getitdone;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseADMIN {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    public static void main(String[] args) throws IOException {
        // Initialize Firebase
        try {
            // start init
            FileInputStream serviceAccount = new FileInputStream("./ServiceAccountKey.json");

            // Initialize the app with a service account, granting admin privileges
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://get-it-done-7a708-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
            Firestore db = FirestoreClient.getFirestore();
            // end init

            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("restricted_access/secret_document");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object document = dataSnapshot.getValue();
                    System.out.println(document);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } catch (IOException err) {
            System.out.println("ERROR: invalid service account credentials.");
            System.out.println(err.getMessage());
            System.exit(1);
        }
    }


    public void newUser() {


    }

    public void getTask() {

    }

    public void deleteTask() {

    }

    public void addCalendar() {

    }

    public void updateCalendar() {

    }
}

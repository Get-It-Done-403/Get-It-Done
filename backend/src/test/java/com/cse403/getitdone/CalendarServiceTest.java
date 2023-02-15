package com.cse403.getitdone;

import com.cse403.getitdone.calendar.CalendarService;
import com.cse403.getitdone.utils.CalendarEntry;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.hibernate.dialect.Database;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CalendarServiceTest {

    public static final String COL_NAME="users";
    public static final String SUBCOL_NAME="tasks";
    public static final String SUBSUBCOL_NAME="entries";

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
    public void testAddCalEntries() throws IOException, ExecutionException, InterruptedException {
        //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        init();
        CalendarEntry as = new CalendarEntry("tio", "uo", "2");
        ApiFuture<WriteResult> write =  db
                .collection(COL_NAME).document("aidan")
                .collection(SUBCOL_NAME).document(as.getTid())
                .collection(SUBSUBCOL_NAME).document(as.getEid())
                .set(as);
        DocumentReference docRef =  db
                .collection(COL_NAME).document("aidan")
                .collection(SUBCOL_NAME).document(as.getTid())
                .collection(SUBSUBCOL_NAME).document(as.getEid());

        ApiFuture<DocumentSnapshot> past = docRef.get();
        DocumentSnapshot document = past.get();
        assert document.exists();

    }
}

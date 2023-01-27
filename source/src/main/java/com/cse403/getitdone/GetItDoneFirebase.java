import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

// Use the application default credentials
GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(credentials)
        .setProjectId(projectId)
        .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();
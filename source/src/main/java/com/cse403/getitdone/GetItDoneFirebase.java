import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


public class FireBase {
    private Firestore db;

    public FireBase(String projectID) throws Exeption {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(projectId)
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .build();
        Firestore db = firestoreOptions.getService();
        this.db = db;
    }

    void addTask(String userID, Task task) throws Exeption {
        DocumentReference docRef = db.collection("user").document(userID).collection(task.dueDate);

        ApiFuture<WriteResults> result = docRef.set(task)




    }

    void runQuery() {}
    void deleteTask() {}

}
package com.cse403.getitdone.calendar;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;
import task.Task;

@Service
public class CalendarService {

    private final Firestore dbFirestor; 
    private final FirebaseDatabase database; 

    public CalendarService() {
        this.dbFirestor = FirestoreClient.getFirestore();
        // how can I connect the database?
        this.database = null;
    }

    // gets remaining tasks for the day
    public List<Task> getRemainingTasks() {
        List<Task> remainingTasks = new ArrayList<>();
        DatabaseReference ref = database.getReference("allocatedTasks");
        // assuming we have a "date" column in the database for the allocated tasks
        ref.orderByChild("date").equalTo(LocalDate.now().toString())
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                        Task task = taskSnapshot.getValue(Task.class);
                        if (tasl.isCompleted == false) {
                            remainingTasks.add(task);
                        }
                        
                    }
                }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            
            }
        });
        return remainingTasks;
    }


    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        // in the table "allocatedTasks"
        DatabaseReference ref = database.getReference("allocatedTasks");
        // assuming we have a "date" column in the database for the allocated tasks
        ref.orderByChild("date").equalTo(LocalDate.now().toString())
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                        Task task = taskSnapshot.getValue(Task.class);
                        if (tasl.isCompleted == true) {
                            completedTasks.add(task);
                        }
                        
                    }
                }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            
            }
        });
        return completedTasks;
    }

    public List<Task> getThisMonth() {
         List<Task> monthTasks = new ArrayList<>();
        DatabaseReference ref = database.getReference("tasks");
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        ref.orderByChild("date")
            .startAt(firstDayOfMonth.toString())
            .endAt(lastDayOfMonth.toString())
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                        Task task = taskSnapshot.getValue(Task.class);
                        monthTasks.add(task);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Handle error
                }
            });
        return monthTasks;
    }
}
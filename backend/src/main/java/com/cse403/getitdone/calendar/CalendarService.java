package com.cse403.getitdone.calendar;

import com.cse403.getitdone.task.Task;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    public List<Task> getRemainingTasks() throws InterruptedException, ExecutionException {
        List<Task> remainingTasks = new ArrayList<>();
        DatabaseReference ref = database.getReference("allocatedTasks");
        // assuming we have a "date" column in the database for the allocated tasks
        ref.orderByChild("date").equalTo(LocalDate.now().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                            Task task = taskSnapshot.getValue(Task.class);
                            if (task.getIsCompleted() == false) {
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


    public List<Task> getCompletedTasks() throws InterruptedException, ExecutionException {
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
                            if (task.getIsCompleted() == true) {
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

    public List<Task> getThisMonth() throws InterruptedException, ExecutionException {
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
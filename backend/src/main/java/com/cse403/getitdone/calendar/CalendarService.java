package com.cse403.getitdone.calendar;

import com.cse403.getitdone.task.Task;
import com.cse403.getitdone.utils.CalendarEntry;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.NonNull;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CalendarService {

    public static final String COL_NAME="users";
    public static final String SUBCOL_NAME="tasks";
    public static final String SUBSUBCOL_NAME="entries";

    //TODO: push to db
    // user tasks   task1 - details, {entry1, entry2...}
    //             task2 - details, {entry1, entry2...}

    public String addCalendarEntries(final String uid, final String tid, final List<CalendarEntry> entries) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        for (CalendarEntry entry: entries) {
            dbFirestore
                    .collection(COL_NAME).document(uid)
                    .collection(SUBCOL_NAME).document(entry.getTid())
                    .collection(SUBSUBCOL_NAME).document(entry.getEid())
                    .set(entry);
        }
        return "uid " + uid + ": entries for task with tid " + tid + " have been added";
    }

}
package com.cse403.getitdone.calendar;

import com.cse403.getitdone.utils.CalendarEntry;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    public static final String COL_NAME="users";
    public static final String SUBCOL_NAME="tasks";
    public static final String SUBSUBCOL_NAME="entries";

    public static String addCalendarEntries(final String uid, final String tid, final List<CalendarEntry> entries) {
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

    public static String addCalendarEntry(final String uid, final String tid, final CalendarEntry entry) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        dbFirestore
                .collection(COL_NAME).document(uid)
                .collection(SUBCOL_NAME).document(entry.getTid())
                .collection(SUBSUBCOL_NAME).document(entry.getEid())
                .set(entry);

        return "uid " + uid + ": entries for task with tid " + tid + " have been added";
    }

}
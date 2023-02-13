package com.cse403.getitdone.googleCalendar;

import com.cse403.getitdone.task.Task;
import com.cse403.getitdone.utils.CalendarEntry;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import static com.cse403.getitdone.googleCalendar.GoogleCal.APPLICATION_NAME;
import static com.cse403.getitdone.googleCalendar.GoogleCal.JSON_FACTORY;

public class ScheduleService {

    private static List<CalendarEntry> entries;

    public String scheduleTask(String uid, final Task task) throws GeneralSecurityException, IOException {

        //scheduleTask() from ScheduleService

        // 1. Get availability from Google Calendar API
        // 2. Break down task and create smaller block (CalendarEntry)
        // 3. add events to calendar API
        // 4. Send those entries to db   task -> entries (this would call a function from CalendarService)

        getUserAvailability(uid, task);

        return null;
    }

    private void getUserAvailability(String uid, final Task task) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleCal.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        DateTime now = new DateTime(System.currentTimeMillis());
        LocalDateTime endTime = task.getDueDate();
        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());

        // Convert LocalDateTime to ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.of(endTime, zone);
        String endTimeStamp = zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        Events events = service.events().list("primary")
                .setTimeMin(now)
                .setTimeMax(DateTime.parseRfc3339(endTimeStamp))
                .execute();
        List<Event> items = events.getItems();
        for (Event item : items) {
            System.out.println(item);
        }

    }
}

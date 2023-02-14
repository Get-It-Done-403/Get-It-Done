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
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import org.checkerframework.checker.units.qual.C;
import org.springframework.cglib.core.Local;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.cse403.getitdone.googleCalendar.GoogleCal.APPLICATION_NAME;
import static com.cse403.getitdone.googleCalendar.GoogleCal.JSON_FACTORY;

public class ScheduleService {

    private ArrayList<CalendarEntry>[] timeSlots;
    private String tid;


    public List<CalendarEntry> scheduleTask(String uid, final Task task) throws GeneralSecurityException, IOException {

        //scheduleTask() from ScheduleService

        // 1. Get availability from Google Calendar API
        // 2. Break down task and create smaller block (CalendarEntry)
        // 3. add events to calendar API
        // 4. Send those entries to db   task -> entries (this would call a function from CalendarService)

        this.tid = task.getTid();
        // get the due date, so we can calculate how many days we have left until the task is due
        ZonedDateTime zonedDate = getZonedDateTime(LocalDateTime.parse(task.getDueDate()));
        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());
        // current date
        ZonedDateTime currDate = ZonedDateTime.now(zone);
        // calculate the remaining days until the task is due
        long daysBetween = ChronoUnit.DAYS.between(currDate, zonedDate) - 1;

        if (daysBetween <= 0) {
            return null;
        }

        // initialize timeslots
        timeSlots = new ArrayList[(int) daysBetween];

        for (int i = 0; i < (int) daysBetween; i++) {
            timeSlots[i] = new ArrayList<CalendarEntry>();
        }

        // get availability from the user's Google Calendar update the timeslots
        getUserAvailability(uid, task);

        // divide up the task and add it to the time slots
        List<CalendarEntry> newEntries = allocateTime(task);

        // set up credentials again to push to the calendar
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleCal.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();


        for (CalendarEntry entry : newEntries) {
            // add to Google Calendar
            Event event = new Event()
                    .setSummary(task.getTitle())
                    .setDescription("Event Scheduled by Get It Done");

            EventDateTime startDateTime = new EventDateTime()
                    .setDateTime(entry.getStartDate())
                    .setTimeZone(userTimeZone.toString());

            EventDateTime endStartDate = new EventDateTime()
                    .setDateTime(entry.getEndDate())
                    .setTimeZone(userTimeZone.toString());

            event.setStart(startDateTime);
            event.setEnd(endStartDate);

            // TODO: add to database. confirm format for the database first.
        }


        return newEntries;
    }

    private void getUserAvailability(String uid, final Task task) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleCal.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        // current time
        DateTime now = new DateTime(System.currentTimeMillis());
        LocalDateTime endTime = LocalDateTime.parse(task.getDueDate());
        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());

        // due date
        ZonedDateTime zonedDateTime = getZonedDateTime(LocalDateTime.parse(task.getDueDate()));
        String endTimeStamp = zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        // get all events from now to due date
        Events events = service.events().list("primary")
                .setTimeMin(now)
                .setTimeMax(DateTime.parseRfc3339(endTimeStamp))
                .execute();
        List<Event> items = events.getItems();

        // get the time zone of the user
        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());

        for (Event item : items) {

            // get the start time for the event
            EventDateTime startTime = item.getStart();
            DateTime startDate = startTime.getDate();
            Instant instant = Instant.ofEpochMilli(startDate.getValue());
            // convert to LocalDateTime
            LocalDateTime localStartDate = LocalDateTime.ofInstant(instant, zone);
            // get the zoned start time, so we can calculate where to put this event in the array
            ZonedDateTime zonedStartTime = getZonedDateTime(localStartDate);

            // note that index 0 means the next day, not today.
            long index = ChronoUnit.DAYS.between(zonedStartTime, zonedDateTime) - 1;

            // placeholder tid for tasks that we got from the api
            String tid = "Pre-existing task";
            // create new calendar entry and add to the timeslot
            CalendarEntry scheduledEvent = new CalendarEntry(tid, startDate, item.getEnd().getDate());
            timeSlots[(int) index].add(scheduledEvent);
        }
    }

    private List<CalendarEntry> allocateTime(Task task) {
        int entryCount = task.getHoursToComplete();


        /* it will be more organized if the task was scheduled around the same time every day
           so, we will come up with a starting time in between 9am and 8pm and try to schedule it at that time every day
           the algorithm will encourage the student to finish as soon as possible, so it will schedule it every day starting the current day

           Things to consider after beta release:
                - option not to schedule on weekends
                - spread out tasks semi-evenly and not schedule it consecutively if time allows
                - try not to schedule it during meal times
                - increase/ decrease time for each calendar entry (not just 1 hour), depending on the student's study habits.
         */

        Random random = new Random();
        // get a random number to represent a time between 9:00 and 20:00
        int defaultTime = random.nextInt(12) + 9;

        // construct a Date object for the default time
        // first find a non-null element in timeslot
        DateTime firstStartDate = null;
        for (int i = 0; i < timeSlots.length; i++) {
            ArrayList<CalendarEntry> oneDay = timeSlots[i];
            if (oneDay != null) {
                for (CalendarEntry entry : oneDay) {
                    firstStartDate = DateTime.parseRfc3339(entry.getStartDate());
                    break;
                }
            }
        }

        // we need to do some converting and make a Date object for the potential time slot which is at defaultTime ending at
        // defaultTime + 1 (assuming that the chunks are only one hour)
        java.util.Calendar calendarStart = java.util.Calendar.getInstance();
        calendarStart.setTime(new Date(firstStartDate.getValue()));
        calendarStart.set(java.util.Calendar.HOUR_OF_DAY, defaultTime);
        // do the same for the end time
        java.util.Calendar calendarEnd = calendarStart;
        calendarStart.set(java.util.Calendar.HOUR_OF_DAY, defaultTime + 1);
        Date potentialStart = calendarStart.getTime();
        Date potentialEnd = calendarEnd.getTime();

        List<CalendarEntry> newEntries = new ArrayList<>();
        for (int i = 0; i < timeSlots.length; i++) {
            ArrayList<CalendarEntry> tmp = new ArrayList<>(timeSlots[i]);
            CalendarEntry newEntry = addEntry(tmp, potentialStart, potentialEnd);
            newEntries.add(newEntry);
        }

        return newEntries;
    }

    private CalendarEntry addEntry(ArrayList<CalendarEntry> schedule, Date potentialStart, Date potentialEnd) {
        CalendarEntry newEntry = null;
        for (CalendarEntry entry : schedule) {

            Date entryStart = new Date(entry.getStartDate().getValue());
            Date entryEnd = new Date(entry.getEndDate().getValue());

            if (entryStart.before(potentialStart) && entryEnd.after(potentialEnd)) {
                /* this means the potential time slot overlaps with the entry
                 we will schedule it after the end of entry time.

                        Things to consider after beta release:
                            - maybe don't schedule the task right after entry, give the user a break
                            - this logic will not work if the task is more than an hour (we assume that entry can be more than an hour)
                */

                // reschedule the potential start
                if (entryEnd.getHours() < 20) {
                    java.util.Calendar calendarStart = java.util.Calendar.getInstance();
                    calendarStart.setTime(potentialStart);
                    calendarStart.set(java.util.Calendar.HOUR_OF_DAY, entryEnd.getHours());
                    potentialStart = calendarStart.getTime();
                    // reschedule the potential end
                    java.util.Calendar calendarEnd = calendarStart;
                    calendarEnd.set(java.util.Calendar.HOUR_OF_DAY, entryEnd.getHours() + 1);
                    potentialEnd = calendarEnd.getTime();

                    schedule.remove(entry);

                    if (schedule != null) {
                        return addEntry(schedule, potentialStart, potentialEnd);
                    }
                }
            } else {
                // set the new entry for the day
                newEntry = new CalendarEntry(this.tid, new DateTime(potentialStart), new DateTime(potentialEnd));
                break;
            }
        }
        return newEntry;
    }


    private ZonedDateTime getZonedDateTime(LocalDateTime time) {
        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());
        // Convert LocalDateTime to ZonedDateTime
        return ZonedDateTime.of(time, zone);
    }
}

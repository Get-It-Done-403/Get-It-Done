package com.cse403.getitdone.googleCalendar;

import com.cse403.getitdone.calendar.CalendarService;
import com.cse403.getitdone.task.Task;
import com.cse403.getitdone.task.TaskService;
import com.cse403.getitdone.utils.CalendarEntry;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.cse403.getitdone.googleCalendar.GoogleCal.APPLICATION_NAME;
import static com.cse403.getitdone.googleCalendar.GoogleCal.JSON_FACTORY;

public class ScheduleService {

    private static boolean[][] timeSlots;
    private static String tid;
    private static DateTime firstStartDate;
    private static Calendar service;
    private static Map<Integer, Set<Integer[]>> scheduledTimes;
    private static ZonedDateTime dueDate;
    private static ZonedDateTime currDate;
    private static final NetHttpTransport HTTP_TRANSPORT;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String scheduleTask(String uid, final Task task) throws GeneralSecurityException, IOException, ExecutionException, InterruptedException {

        //scheduleTask() from ScheduleService

        // 1. Get availability from Google Calendar API
        // 2. Break down task and create smaller block (CalendarEntry)
        // 3. add events to calendar API
        // 4. Send those entries to db   task -> entries (this would call a function from CalendarService)

        scheduledTimes = new HashMap<>();
        service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleCal.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();


        tid = task.getTid();
        // get the due date, so we can calculate how many days we have left until the task is due
        String taskDate = task.getDueDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        LocalDateTime localDate = LocalDateTime.parse(taskDate, formatter);
        ZonedDateTime zonedDate = getZonedDateTime(localDate);
        dueDate = zonedDate;

        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());
        // current date
        currDate = ZonedDateTime.now(zone);

        // calculate the remaining days until the task is due (we assume that the due date is 20:00 of the zonedDate)
        long daysBetween = ChronoUnit.DAYS.between(currDate, zonedDate) + 1;


        if (daysBetween < 0) {
            return "Error: due date not in the future";
        }

        timeSlots = new boolean[(int) daysBetween + 1][11];

        // get availability from the user's Google Calendar update the timeslots
        getUserAvailability(uid, task);
        int availableHours = 0;

        // count available hours

        for (int i = 0; i < timeSlots.length; i++) {
            boolean[] oneDay = timeSlots[i];
            for (int j = 0; j < oneDay.length; j++) {
                if (!oneDay[j]) {
                    availableHours++;
                }
            }
        }

        if (availableHours < task.getHoursToComplete()) {
            return "Error: Not enough available hours";
        }
        TaskService.saveTaskDetails(uid, task);
        // divide up the task and add it to the time slots
        List<CalendarEntry> newEntries = allocateTime(task);

        for (CalendarEntry entry : newEntries) {
            // add to Google Calendar
            Event event = new Event()
                    .setSummary(task.getTitle())
                    .setDescription("Event Scheduled by Get It Done");

            EventDateTime startDateTime = new EventDateTime()
                    .setDateTime(new DateTime(entry.getStartDate()))
                    .setTimeZone(userTimeZone.getID());

            EventDateTime endStartDate = new EventDateTime()
                    .setDateTime(new DateTime(entry.getEndDate()))
                    .setTimeZone(userTimeZone.getID());

            event.setStart(startDateTime);
            event.setEnd(endStartDate);

            String calendarId = "primary";
            // event created
            service.events().insert(calendarId, event).execute();

        }
        // add to database

        CalendarService.addCalendarEntries(uid, tid, newEntries);

        return "Success: Events Scheduled";
    }

    private static void getUserAvailability(String uid, final Task task) throws GeneralSecurityException, IOException {

        // current time
        DateTime now = new DateTime(System.currentTimeMillis());
        // due date
        String taskDate = task.getDueDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        LocalDateTime localDate = LocalDateTime.parse(taskDate, formatter);
        // get all events from now to due date
        Events events = service.events().list("primary")
                .setTimeMin(now)
                .setTimeMax(DateTime.parseRfc3339(taskDate))
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        // get the time zone of the user
        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());
        DateTime tmpDate = new DateTime(System.currentTimeMillis());
        java.util.Calendar temp = java.util.Calendar.getInstance();
        temp.setTime(new Date(tmpDate.getValue()));

        firstStartDate = new DateTime(temp.getTime());

        for (int i = 0; i < items.size(); i++) {
            Event item = items.get(i);
            // get the start time for the event
            EventDateTime startTime = item.getStart();


            DateTime startDate = startTime.getDateTime();
            Instant instantStart = Instant.ofEpochMilli(startDate.getValue());
            // convert to LocalDateTime
            LocalDateTime localStartDate = LocalDateTime.ofInstant(instantStart, zone);
            // get the zoned start time, so we can calculate where to put this event in the array
            ZonedDateTime zonedStartTime = getZonedDateTime(localStartDate);

            EventDateTime endTime = item.getEnd();
            DateTime endDate = endTime.getDateTime();
            Instant instantEnd = Instant.ofEpochMilli(endDate.getValue());
            // convert to LocalDateTime
            LocalDateTime localEndDate = LocalDateTime.ofInstant(instantEnd, zone);
            // get the zoned start time, so we can calculate where to put this event in the array
            ZonedDateTime zonedEndTime = getZonedDateTime(localEndDate);

            int startDay = zonedStartTime.getDayOfMonth();
            int endDay = zonedEndTime.getDayOfMonth();
            // check if the event takes up more than one day
            if (startDay != endDay) {
                // divide up hours if it overlaps

                if (zonedStartTime.getHour() < 20) {
                    int firstStartHour = zonedStartTime.getHour();
                    int firstEndHour = 20;
                    setUserAvailability(firstStartHour, firstEndHour, startDay);
                }

                if (zonedEndTime.getHour() > 9) {
                    int secondStartHour = 9;
                    int secondEndHour = zonedEndTime.getHour();

                    setUserAvailability(secondStartHour, secondEndHour, endDay);
                }

            } else {
                // the event is within a day
                int startHour = zonedStartTime.getHour();
                int endHour = zonedEndTime.getHour();
                setUserAvailability(startHour, endHour, startDay);
            }
        }
        // set availability to true (meaning you cannot schedule new tasks) if the due time is before 20:00 and also if the current time is after 9:00
        int dueHour = dueDate.getHour();
        int currHour = currDate.getHour();
        if (dueHour < 20 && dueHour > 9) {
            for (int i = dueHour - 9; i < timeSlots[0].length; i++) {
                timeSlots[timeSlots.length - 1][i] = true;
            }
        }
        if (currHour > 9 && currHour < 20) {
            for (int i = 0; i < currHour - 9; i++) {
                timeSlots[0][i] = true;
            }
        }

    }

    private static void setUserAvailability(int startHour, int endHour, int day) {
        if (startHour < 9) {
            startHour = 9;
        }
        if (endHour > 20) {
            endHour = 20;
        }
        java.util.Calendar temp = java.util.Calendar.getInstance();
        temp.setTime(new Date(firstStartDate.getValue()));
        int firstDay = temp.get(java.util.Calendar.DAY_OF_MONTH);
        int numHours = endHour - startHour;
        for (int i = 0; i < numHours; i++) {
            timeSlots[day - firstDay][startHour + i - 9] = true;
        }
    }


    private static List<CalendarEntry> allocateTime(Task task) {
        int taskDuration = task.getHoursToComplete();

        Random random = new Random();
        // get a random number to represent a time between 9:00 and 19:00
        int defaultTime = random.nextInt(11) + 9;
        int taskRemaining = taskDuration;
        java.util.Calendar temp = java.util.Calendar.getInstance();
        temp.setTime(new Date(firstStartDate.getValue()));
        int firstDay = temp.get(java.util.Calendar.DAY_OF_MONTH);
        Set<Integer> addedDays = new HashSet<>();

        while (taskRemaining > 0) {
                int day = random.nextInt(timeSlots.length) + firstDay;
                addedDays.add(day);
                if (addedDays.size() == timeSlots.length - 1) {
                    defaultTime = random.nextInt(11) + 9;
                    addedDays.clear();
                }

                boolean[] oneDay = timeSlots[day - firstDay];

                if (!oneDay[defaultTime - 9]) {
                    if (!scheduledTimes.containsKey(day)) {
                        scheduledTimes.put(day, new HashSet<>());
                    }
                    oneDay[defaultTime - 9] = true;
                    Integer[] startEnd = new Integer[2];
                    startEnd[0] = defaultTime;
                    Integer endTime = defaultTime + 1;
                    startEnd[1] = endTime;

                    scheduledTimes.get(day).add(startEnd);
                    taskRemaining--;
                    if (taskRemaining == 0) {
                        break;
                    }
                } else {
                    int up = defaultTime + 1;
                    int down = defaultTime - 1;
                    int scheduledTime = -1;
                    while (up - 9 < oneDay.length || down - 9 >= 0) {
                        if (up - 9 < oneDay.length && !oneDay[up - 9]) {
                            scheduledTime = up;
                            oneDay[up - 9] = true;
                            break;

                        }
                        if (down - 9 >= 0 && !oneDay[down - 9]) {
                            scheduledTime = down;
                            oneDay[down - 9] = true;
                            break;
                        }

                        up++;
                        down--;
                    }

                    if (scheduledTime != -1) {
                        if (!scheduledTimes.containsKey(day)) {
                            scheduledTimes.put(day, new HashSet<>());
                        }
                        Integer[] startEnd = new Integer[2];
                        startEnd[0] = scheduledTime;
                        startEnd[1] = scheduledTime + 1;
                        scheduledTimes.get(day).add(startEnd);
                        taskRemaining--;
                        if (taskRemaining == 0) {
                            break;
                        }
                    }
                }

        }

        List<CalendarEntry> newEntries = new ArrayList<>();
        for (int day : scheduledTimes.keySet()) {
            for (Integer[] startEnd : scheduledTimes.get(day)) {
                int startHour = startEnd[0];
                int endHour = startEnd[1];
                java.util.Calendar calendarStart = java.util.Calendar.getInstance();
                calendarStart.setTime(new Date(firstStartDate.getValue()));
                calendarStart.set(java.util.Calendar.DAY_OF_MONTH, day);
                calendarStart.set(java.util.Calendar.HOUR_OF_DAY, startHour);
                calendarStart.set(java.util.Calendar.MINUTE, 0);
                calendarStart.set(java.util.Calendar.SECOND, 0);
                calendarStart.set(java.util.Calendar.MILLISECOND, 0);
                // maybe check later if the time here is accurate
                Date startTime = calendarStart.getTime();

                java.util.Calendar calendarEnd = java.util.Calendar.getInstance();
                calendarEnd.setTime(new Date(firstStartDate.getValue()));
                calendarEnd.set(java.util.Calendar.DAY_OF_MONTH, day);
                calendarEnd.set(java.util.Calendar.HOUR_OF_DAY, endHour);
                calendarEnd.set(java.util.Calendar.MINUTE, 0);
                calendarEnd.set(java.util.Calendar.SECOND, 0);
                calendarEnd.set(java.util.Calendar.MILLISECOND, 0);
                // maybe check later if the time here is accurate
                Date endTime = calendarEnd.getTime();

                CalendarEntry newEntry = new CalendarEntry(tid, new DateTime(startTime).toStringRfc3339(), new DateTime(endTime).toStringRfc3339());
                newEntries.add(newEntry);
            }
        }
        return newEntries;
    }

    private static ZonedDateTime getZonedDateTime(LocalDateTime time) {
        TimeZone userTimeZone = TimeZone.getDefault();
        ZoneId zone = ZoneId.of(userTimeZone.getID());
        // Convert LocalDateTime to ZonedDateTime
        return ZonedDateTime.of(time, zone);
    }
}
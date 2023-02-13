package com.cse403.getitdone.googleCalendar;

import com.cse403.getitdone.task.Task;
import com.cse403.getitdone.utils.CalendarEntry;

import java.util.List;

public class ScheduleService {

    private static List<CalendarEntry> entries;

    public String scheduleTask(final Task task) {

        //scheduleTask() from ScheduleService

        // 1. Get availability from Google Calendar API
        // 2. Break down task and create smaller block (CalendarEntry)
        // 3. add events to calendar API
        // 4. Send those entries to db   task -> entries (this would call a function from CalendarService)

        return null;
    }
}

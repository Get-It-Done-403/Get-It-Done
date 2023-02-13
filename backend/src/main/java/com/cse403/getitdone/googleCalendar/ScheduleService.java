package com.cse403.getitdone.googleCalendar;

import com.cse403.getitdone.task.Task;
import com.cse403.getitdone.utils.CalendarEntry;

import java.util.List;

public class ScheduleService {

    private static List<CalendarEntry> entries;

    public String scheduleTask(final Task task) {

        for(int i = 0; i < task.getHoursToComplete(); i++) {
            CalendarEntry entry = new CalendarEntry();

        }

        return null;
    }
}

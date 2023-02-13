package com.cse403.getitdone.calendar;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.cse403.getitdone.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @GetMapping("/getCompletedTasksToday")
    public List<Task> getCompletedTasksToday() throws InterruptedException, ExecutionException {
        return calendarService.getCompletedTasks();
    }

}
package com.cse403.getitdone.calendar;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.cse403.getitdone.task.Task;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/getRemainingTasksToday")
    public List<Task> getRemainingTasksToday() throws InterruptedException, ExecutionException {
        return calendarService.getRemainingTasks();
    }

    @GetMapping("/getCompletedTasksToday")
    public List<Task> getCompletedTasksToday() throws InterruptedException, ExecutionException {
        return calendarService.getCompletedTasks();
    }

    @GetMapping("/getThisMonth")
    public List<Task> getThisMonth() throws InterruptedException, ExecutionException {
        return calendarService.getThisMonth();
    }

//    @PostMapping("/allocateTask")
//    public Calendar allocateTask(@RequestBody Task task) throws InterruptedException, ExecutionException {
//        return calendarService.allocateTask(task);
//    }
}
package com.cse403.getitdone.calendar;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import task.Task;

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

    @PostMapping("/allocateTask")
    public Calendar allocateTask(@RequestBody Task task) throws InterruptedException, ExecutionException {
        return calendarService.allocateTask(task);
    }
}
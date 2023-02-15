package com.cse403.getitdone.task;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/getTaskDetails")
    public Task getTask(@RequestParam String uid, @RequestParam String tid ) throws InterruptedException, ExecutionException {
        return taskService.getTaskDetails(uid, tid);
    }

    @PostMapping("/createTask")
    public String createTask(@RequestParam String uid, @RequestBody Task task ) throws InterruptedException, ExecutionException {
        //scheduleTask() from ScheduleService

            // 1. Get availability from Google Calendar API
            // 2. Break down task and create smaller block (CalendarEntry)
            // 3. add events to calendar API
            // 4. Send those entries to db   task -> entries
        System.out.println(task.toString());

        return taskService.saveTaskDetails(uid,task);
    }

    @PostMapping("/updateTask")
    public String updateTask(@RequestParam String uid, @RequestBody Task task ) throws InterruptedException, ExecutionException {
        return taskService.saveTaskDetails(uid,task);
    }

    @DeleteMapping("/deleteTask")
    public String deleteTask(@RequestParam String uid, @RequestParam String tid){
        return taskService.deleteTask(uid, tid);
    }

    @GetMapping("/getTaskList")
    public List<Task> getTask(@RequestParam String uid) throws InterruptedException, ExecutionException {
        return taskService.getTaskList(uid);
    }
}

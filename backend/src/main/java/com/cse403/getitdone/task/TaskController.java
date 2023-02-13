package com.cse403.getitdone.task;

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
        //scheduleTask() from Calendar
            // BREAK DOWN TASKS AND SEND COLLECTIONS OF EVENTS TO FIREBASE.
            // CREATE EVENTS AND SEND TO GOOGLE CALENDAR API
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
}

package com.cse403.getitdone.task;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @PostMapping("/{uid}/createTask", title = )
    public ResponseEntity<Object> createTask(@PathVariable String uid, @RequestBody Task task) {
        Task newTask = TaskRepository.createTask(task);

    }
}

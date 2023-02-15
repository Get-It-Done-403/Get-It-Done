package com.cse403.getitdone.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUserName")
    public String getUserName(@RequestParam String uid) throws ExecutionException, InterruptedException {
        return userService.getUserName(uid);
    }

    @PostMapping("/createUserName")
    public String createUserName(@RequestParam String uid, @RequestParam String userName) throws ExecutionException, InterruptedException {
        return userService.saveUserName(uid, userName);
    }
}

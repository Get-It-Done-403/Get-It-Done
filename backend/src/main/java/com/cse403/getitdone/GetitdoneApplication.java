package com.cse403.getitdone;

import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetitdoneApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(GetitdoneApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}

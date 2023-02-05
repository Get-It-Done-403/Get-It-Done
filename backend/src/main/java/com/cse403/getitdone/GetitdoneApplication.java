package com.cse403.getitdone;

import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class GetitdoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetitdoneApplication.class, args);
    }

}

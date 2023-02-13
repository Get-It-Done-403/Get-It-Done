package com.cse403.getitdone;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

@SpringBootApplication
@EntityScan
public class GetitdoneApplication {

    public static void main(String[] args) {
        ClassLoader classLoader = GetitdoneApplication.class.getClassLoader();
        try {
            //File file = new File(Objects.requireNonNull(classLoader.getResource("/Users/aidanpetta/IdeaProjects/Get-It-Done/backend/src/main/java/com/cse403/getitdone/servicekey.json")).getFile());
            FileInputStream serciceAcc = new FileInputStream("/Users/aidanpetta/IdeaProjects/Get-It-Done/backend/src/main/resources/servicekey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serciceAcc))
                    .setDatabaseUrl("https://get-it-done-7a708-default-rtdb.firebaseio.com")
                    .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        SpringApplication.run(GetitdoneApplication.class, args);
    }

}

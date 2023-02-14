package com.cse403.getitdone;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;


//@Service
public class FirebaseADMIN {

    //@PostConstruct
    public void init() {
        try {
            FileInputStream serciceAcc = new FileInputStream("/Users/admin/Get-It-Done/backend/src/main/resources/servicekey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serciceAcc))
//                    .setDatabaseUrl("https://get-it-done-7a708-default-rtdb.firebaseio.com")
                    .setDatabaseUrl("https://get-it-done-dev-ee59c-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
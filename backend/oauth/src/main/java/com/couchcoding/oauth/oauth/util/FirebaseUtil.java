package com.couchcoding.oauth.oauth.util;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
public class FirebaseUtil {
    private FirebaseAuth firebaseAuth;

    @PostConstruct
    public void initializeFirebase() throws IOException{
        ClassPathResource resource = new ClassPathResource("config/firebaseKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
            .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        firebaseAuth = FirebaseAuth.getInstance(app);
    }
    
    public FirebaseToken verifyAuthorizationHeader(String authorizationHeader) {
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid authentication header"); 
        }
        authorizationHeader = authorizationHeader.replace("Bearer ", "");
        try{
            return firebaseAuth.verifyIdToken(authorizationHeader);
        } catch(FirebaseAuthException e) {
            throw new IllegalArgumentException("Invalid firebase token"); 
        }
    }
}

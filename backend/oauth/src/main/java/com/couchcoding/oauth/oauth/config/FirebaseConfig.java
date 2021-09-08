package com.couchcoding.oauth.oauth.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.springframework.context.annotation.Bean;

public class FirebaseConfig {
    @Bean
    public FirebaseAuth firebaseAuth() {
        FirebaseApp firebaseApp = FirebaseApp.initializeApp();
        return FirebaseAuth.getInstance(firebaseApp);
    }
}

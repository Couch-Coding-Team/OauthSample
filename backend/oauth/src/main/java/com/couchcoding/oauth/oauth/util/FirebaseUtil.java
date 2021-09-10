package com.couchcoding.oauth.oauth.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class FirebaseUtil {
    @Autowired
    private FirebaseAuth firebaseAuth;
    
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

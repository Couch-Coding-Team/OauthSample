package com.couchcoding.oauth.oauth.controller;

import com.couchcoding.oauth.oauth.entity.CustomUser;
import com.couchcoding.oauth.oauth.service.CustomUserDetailsService;
import com.couchcoding.oauth.oauth.util.FirebaseUtil;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private FirebaseUtil firebaseUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("")
    public CustomUser register(@RequestHeader("Authorization") String authorization, RegisterInfo registerInfo) {
        log.info("register request");
        FirebaseToken decodedToken = firebaseUtil.verifyAuthorizationHeader(authorization);
        return customUserDetailsService.register(decodedToken.getUid(), registerInfo);
    }

    @GetMapping("/me")
    public CustomUser getUserMe(Authentication authentication) {
        return (CustomUser) authentication.getPrincipal();
    }
}

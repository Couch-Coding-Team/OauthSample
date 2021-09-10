package com.couchcoding.oauth.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.couchcoding.oauth.oauth.controller.RegisterInfo;
import com.couchcoding.oauth.oauth.dao.*;
import com.couchcoding.oauth.oauth.entity.CustomUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).get();
    }

    public void register(String uid, RegisterInfo registerInfo) {
        CustomUser customUser = new CustomUser();
        customUser.setUsername(uid);
        customUser.setEmail(registerInfo.getEmail());
        customUser.setNickName(registerInfo.getNickName());
        userRepository.save(customUser);
    }
}

package com.couchcoding.oauth.oauth.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.couchcoding.oauth.oauth.domain.user.entity.CustomUser;
import com.couchcoding.oauth.oauth.domain.user.repository.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).get();
    }

    public CustomUser register(String uid, String email, String nickName) {
        CustomUser customUser = new CustomUser();
        customUser.setUsername(uid);
        customUser.setNickname(nickName);
        customUser.setEmail(email);
        userRepository.save(customUser);
        return customUser;
    }
}

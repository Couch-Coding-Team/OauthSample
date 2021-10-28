package com.couchcoding.oauth.oauth.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.couchcoding.oauth.oauth.domain.user.dao.*;
import com.couchcoding.oauth.oauth.domain.user.entity.CustomUser;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).get();
    }

    @Transactional
    public CustomUser register(String uid, String email, String nickname) {
        CustomUser customUser = CustomUser.builder()
                .username(uid)
                .email(email)
                .nickname(nickname)
                .build();
        userRepository.save(customUser);
        return customUser;
    }
}

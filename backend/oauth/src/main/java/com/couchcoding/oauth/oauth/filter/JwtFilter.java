package com.couchcoding.oauth.oauth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couchcoding.oauth.oauth.entity.CustomUser;
import com.couchcoding.oauth.oauth.service.CustomUserDetailsService;
import com.couchcoding.oauth.oauth.util.FirebaseUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter{
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private FirebaseUtil firebaseUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        
        String authorizationHeader = request.getHeader("Authorization");
        FirebaseToken decodedToken = null;

        try{
            decodedToken = firebaseUtil.verifyAuthorizationHeader(authorizationHeader);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpStatus.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid());

        if(user == null){
            response.sendError(HttpStatus.SC_UNAUTHORIZED, "User not found");
            return;
        }
        
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        filterChain.doFilter(request, response);
    }
}

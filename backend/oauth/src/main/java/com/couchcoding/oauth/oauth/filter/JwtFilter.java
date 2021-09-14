package com.couchcoding.oauth.oauth.filter;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couchcoding.oauth.oauth.domain.user.service.CustomUserService;
import com.couchcoding.oauth.oauth.util.FirebaseUtil;
import com.google.firebase.auth.FirebaseToken;

import org.apache.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtFilter extends OncePerRequestFilter{

    private CustomUserService userDetailsService;
    private FirebaseUtil firebaseUtil;

    public JwtFilter(CustomUserService userDetailsService, FirebaseUtil firebaseUtil) {
        this.userDetailsService = userDetailsService;
        this.firebaseUtil = firebaseUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        
        String authorizationHeader = request.getHeader("Authorization");
        FirebaseToken decodedToken = null;

        try{
            decodedToken = firebaseUtil.verifyAuthorizationHeader(authorizationHeader);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
            return;
        }

        try{
            UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch(NoSuchElementException e){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":\"USER_NOT_FOUND\"}");
            return;
        }
    }
}

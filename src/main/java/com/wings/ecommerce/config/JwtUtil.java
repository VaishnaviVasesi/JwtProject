package com.wings.ecommerce.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public User getUser(final String token){
        return null;
    }
    public String generateToken(String username){
        return null;
    }

    public void validateToken(final String token){

    }
}

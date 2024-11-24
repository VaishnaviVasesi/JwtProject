package com.wings.ecommerce.config;

import com.wings.ecommerce.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.validity}")
    private long expiry;
    public String generateToken(com.wings.ecommerce.models.User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getUsername());
    }
    private String createToken(Map<String, Object> claims,String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    private Claims getClaim(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    private boolean isTokenExpired(String token){
        return getClaim(token).getExpiration().before(new Date());
    }

    public boolean isValidToken(String token, User user){
        return getClaim(token).getSubject().equals(user.getUsername()) && !isTokenExpired(token);
    }

}

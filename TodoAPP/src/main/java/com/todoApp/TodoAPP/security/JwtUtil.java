package com.todoApp.TodoAPP.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY="YOUR_SECRET_KEY_H";
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public  String extractUsername(String token){
        return  extractClaims(token).getSubject();
    }

    public  boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
    public boolean ValidateToken(String token, String username){
        String tokenUsername= extractUsername(token);
        return (tokenUsername.equals(username)&&!isTokenExpired(token));
    }
    private Claims extractClaims(String token){
        return  Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

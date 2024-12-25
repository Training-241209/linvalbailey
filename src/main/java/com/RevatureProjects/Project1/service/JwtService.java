package com.RevatureProjects.Project1.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.RevatureProjects.Project1.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
     private String secretKey;

    /**
     * Generates a JWT token for the specified user.
     *
     * @param user the user for whom the token is to be generated
     * @return a JWT token as a String
     */
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("id", user.getUserId())
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .claim("firstname", user.getFirstName())
                .claim("lastname", user.getLastName())
                .claim("password", user.getPassword())
                // Add other fields
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) // 15 minutes
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Decodes the given JWT token and retrieves the subject (email) from it.
     *
     * @param token the JWT token to decode
     * @return the subject (email) contained in the token
     * @throws io.jsonwebtoken.JwtException if the token is invalid or expired
     */
    public User decodeToken(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new User(claims.get("id", Integer.class), claims.get("username", String.class),claims.get("firstname", String.class),
        claims.get("lastname", String.class),claims.get("password", String.class),claims.get("role", String.class));
    }
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

/*  public Integer getIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id", Integer.class);
                
    }*/
}

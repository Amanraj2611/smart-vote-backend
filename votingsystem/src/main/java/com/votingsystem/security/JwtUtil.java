package com.votingsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ secret key (must be long)
    private final String SECRET =
            "mySuperSecretKeyForJwtAuthenticationVotingSystem2026";

    // ===== CREATE SIGNING KEY =====
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ===== GENERATE TOKEN =====
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()
                        + 1000 * 60 * 60 * 24)) // 10 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ===== EXTRACT USERNAME =====
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ===== EXTRACT ROLE =====
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ===== VALIDATE TOKEN =====
    public boolean validateToken(String token, String email) {
        return email.equals(extractUsername(token))
                && !isTokenExpired(token);
    }

    // ===== CHECK EXPIRATION =====
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // ===== GET CLAIMS =====
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
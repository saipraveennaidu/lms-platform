package com.lms.backend.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lms.backend.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(
                    System.currentTimeMillis() + jwtExpiration
                ))
                .signWith(getSigningKey())
                .compact();
    }

    // extractAllClaims was used to extract username, roles, Exp, issued. Instead of repeated useage of this
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())    // verification havent happend yet, it just stores secretKey obj
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // check whether username in token = username in userDetails obj and not expired
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}

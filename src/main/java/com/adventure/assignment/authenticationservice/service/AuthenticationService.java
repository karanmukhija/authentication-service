package com.adventure.assignment.authenticationservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Slf4j
public class AuthenticationService {
    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public String generateToken(String role) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        String jwtToken = Jwts.builder()
                .setIssuer("adventure")
                .setSubject("JWT Token")
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 30000000))
                .signWith(secretKey)
                .compact();

        log.info("Token Generated Successfully");
        return jwtToken;
    }

    public ResponseEntity<?> validateToken(String jwtToken) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(
                    JWT_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            log.info("Token Validated Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(claims.get("role").toString());
        } catch (Exception e){
            log.error("Invalid Token Received");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
        }
    }

}

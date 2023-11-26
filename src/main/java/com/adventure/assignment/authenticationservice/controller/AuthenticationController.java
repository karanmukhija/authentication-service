package com.adventure.assignment.authenticationservice.controller;

import com.adventure.assignment.authenticationservice.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/generate/userToken")
    public ResponseEntity<?> generateUserToken() {
        log.info("Generating User Token");
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.generateToken("ROLE_USER"));
    }

    @GetMapping("/generate/adminToken")
    public ResponseEntity<?> generateAdminToken() {
        log.info("Generating Admin Token");
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.generateToken("ROLE_ADMIN"));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String jwtToken) {
        log.info("Validating Token");
        return authenticationService.validateToken(jwtToken);
    }
}

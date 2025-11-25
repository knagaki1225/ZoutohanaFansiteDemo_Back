package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.auth.AuthenticationService;
import com.example.zoutohanafansitedemo.entity.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authenticationService.authentication(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse userRegisterResponse = authenticationService.registerUser(userRegisterRequest);
        return ResponseEntity.ok(userRegisterResponse);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<UserRegisterResponse> passwordReset(@RequestBody PasswordResetRequest passwordResetRequest) {
        UserRegisterResponse userRegisterResponse = authenticationService.passwordReset(passwordResetRequest);
        return ResponseEntity.ok(userRegisterResponse);
    }
}

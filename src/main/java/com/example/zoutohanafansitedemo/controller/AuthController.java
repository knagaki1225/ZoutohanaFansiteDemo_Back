package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.auth.AuthenticationService;
import com.example.zoutohanafansitedemo.entity.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authenticationService.adminAuthentication(loginRequest);
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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/validate")
    public ResponseEntity<?> validate(){
        return ResponseEntity.ok().build();
    }
}
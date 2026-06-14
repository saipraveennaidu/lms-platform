package com.lms.backend.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.auth.dto.LoginRequest;
import com.lms.backend.auth.dto.LoginResponse;
import com.lms.backend.auth.dto.RegisterRequest;
import com.lms.backend.auth.dto.RegisterResponse;
import com.lms.backend.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // to test JWT token
    // @GetMapping("/test")
    // public String test() {
    //     return "JWT Working";
    // }

    
}

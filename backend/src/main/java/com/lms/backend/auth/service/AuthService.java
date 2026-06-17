package com.lms.backend.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.backend.auth.dto.LoginRequest;
import com.lms.backend.auth.dto.LoginResponse;
import com.lms.backend.auth.dto.RegisterRequest;
import com.lms.backend.auth.dto.RegisterResponse;
import com.lms.backend.common.exception.EmailAlreadyExistsException;
import com.lms.backend.common.exception.InvalidCredentialsException;
import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.user.entity.User;
import com.lms.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return new RegisterResponse(user.getId(), 
                    user.getName(), 
                    user.getEmail(), 
                    user.getRole(), 
                    "User registered successfully"
        );
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
            "Login successful",
            token,
            "Bearer "
        );
    }
}

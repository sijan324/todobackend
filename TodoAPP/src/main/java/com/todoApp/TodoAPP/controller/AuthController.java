package com.todoApp.TodoAPP.controller;

import com.todoApp.TodoAPP.DTOs.JwtRequest;
import com.todoApp.TodoAPP.DTOs.JwtResponse;
import com.todoApp.TodoAPP.model.User;
import com.todoApp.TodoAPP.repository.UserRepository;
import com.todoApp.TodoAPP.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Login endpoint
    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new JwtResponse(token);
    }

    // Register endpoint
    @PostMapping("/register")
    public String register(@RequestBody JwtRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "User already exists";
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(newUser);

        return "User registered successfully";
    }
}

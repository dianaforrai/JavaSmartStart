package com.example.ems.controller;

import com.example.ems.dto.JwtAuthResponse;
import com.example.ems.dto.LoginDto;
import com.example.ems.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.authenticate(loginDto);
        return ResponseEntity.ok(new JwtAuthResponse

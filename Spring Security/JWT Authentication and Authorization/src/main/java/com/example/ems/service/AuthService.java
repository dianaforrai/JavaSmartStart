package com.example.ems.service;

import com.example.ems.dto.LoginDto;

public interface AuthService {
    String authenticate(LoginDto loginDto);
}

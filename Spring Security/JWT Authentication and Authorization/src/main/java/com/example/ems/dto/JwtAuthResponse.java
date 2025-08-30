package com.example.ems.dto;

public class JwtAuthResponse {
    private String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() { return token; }
}

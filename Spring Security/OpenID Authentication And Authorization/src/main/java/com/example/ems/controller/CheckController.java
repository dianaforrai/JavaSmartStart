package com.example.ems.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/api/test")
    public String test(OAuth2AuthenticationToken token) {
        System.out.println("Principal: " + token.getPrincipal());
        return "Hello, World!";
    }
}

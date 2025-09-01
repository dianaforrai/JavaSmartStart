package com.example.ems.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(OAuth2AuthenticationToken token, Model model) {
        model.addAttribute("user", token != null ? token.getPrincipal() : null);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

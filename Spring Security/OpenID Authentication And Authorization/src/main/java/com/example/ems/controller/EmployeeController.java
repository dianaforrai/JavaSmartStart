package com.example.ems.controller;

import com.example.ems.repo.EmployeeRepository;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/employees")
    public String employees(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("employees", repo.findAll());
        model.addAttribute("user", token.getPrincipal());
        return "employees";
    }
}

package com.example.ems.controller;

import com.example.ems.repo.EmployeeRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    public String employees(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("employees", repo.findAll());
        model.addAttribute("user", user);
        return "employees";
    }
}

package com.example.hw7.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String homePage(Authentication authenticator, Model model) {
        model.addAttribute("username",
                (authenticator != null) ? authenticator.getName() : "dear friend, authentication please");
        return "index";
    }

    @GetMapping("/public-data")
    public String getPublicResource() {
        return "public-data";
    }

    @GetMapping("/private-data")
    public String getPrivateResource() {
        return "private-data";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/";
    }

}

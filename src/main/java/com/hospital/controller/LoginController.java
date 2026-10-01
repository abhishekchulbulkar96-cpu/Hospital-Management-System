package com.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if(username.equals("admin")
                && password.equals("admin")) {

            return "dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid Username or Password");

        return "login";
    }
}
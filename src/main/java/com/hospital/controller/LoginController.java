package com.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {

        if(username.equals("admin")
                && password.equals("admin123")) {

            return "redirect:/dashboard";
        }

        return "redirect:/";
    }
}
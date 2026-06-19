package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.UserDTO;
import com.hospital.entity.UserLogin;
import com.hospital.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserLogin addUser(
            @RequestBody UserDTO dto) {

        return service.saveUser(dto);
    }

    @GetMapping
    public List<UserLogin> getUsers() {
        return service.getAllUsers();
    }
}
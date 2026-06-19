package com.hospital.service;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.UserDTO;
import com.hospital.entity.UserLogin;
import com.hospital.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepository repo;

    public UserLogin saveUser(UserDTO dto) {

        UserLogin user = new UserLogin();

        user.setUsername(dto.getUsername());

        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return repo.save(user);
    }

    public List<UserLogin> getAllUsers() {
        return repo.findAll();
    }
    
    
    
}
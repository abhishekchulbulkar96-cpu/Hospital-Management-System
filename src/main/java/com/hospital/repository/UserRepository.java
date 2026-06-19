package com.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.UserLogin;

public interface UserRepository
        extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findByUsername(String username); //custom repository to find the username from the database
}
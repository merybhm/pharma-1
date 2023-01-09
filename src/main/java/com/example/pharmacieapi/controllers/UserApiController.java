package com.example.pharmacieapi.controllers;

import com.example.pharmacieapi.entity.User;
import com.example.pharmacieapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UserApiController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/test")
    public String testEndpoint(){
        return "Test end point is working";
    }
    @GetMapping("/all")
    public Iterable<User> getAll(){
        return userRepository.findAll();
    }
}

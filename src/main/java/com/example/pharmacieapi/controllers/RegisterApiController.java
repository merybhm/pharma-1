package com.example.pharmacieapi.controllers;

import com.example.pharmacieapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class RegisterApiController {

    @Autowired
    UserService userService;


    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestBody HashMap<String,String> map){


        if(map.get("first_name").isEmpty() || map.get("last_name").isEmpty() || map.get("email").isEmpty() || map.get("password").isEmpty()){
            return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);
        }

        // Encrypt / Hash  Password:
        String hashed_password = BCrypt.hashpw(map.get("password"), BCrypt.gensalt());

        // Register New User:
        int result = userService.registerNewUserServiceMethod(map.get("first_name"), map.get("last_name"), map.get("email"), hashed_password);

        if(result != 1){
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);

    }
}
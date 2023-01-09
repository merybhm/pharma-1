package com.example.pharmacieapi.controllers;

import com.example.pharmacieapi.entity.Login;
import com.example.pharmacieapi.entity.Pharmacie;
import com.example.pharmacieapi.entity.User;
import com.example.pharmacieapi.service.PharmacieService;
import com.example.pharmacieapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class LoginApiController {

    @Autowired
    UserService userService;
    @Autowired
    PharmacieService pharmacieService;

    @PostMapping("/user/login")
    public ResponseEntity authenticateUser(@RequestBody Login login){

        // Get User Email:
        List<String> userEmail = userService.checkUserEmail(login.getEmail());

        // Check If Email Is Empty:
        if(userEmail.isEmpty() || userEmail == null){
            return new ResponseEntity("Email does not exist", HttpStatus.NOT_FOUND);
        }
        // End Of Check If Email Is Empty.

        // Get Hashed User Password:
        String hashed_password = userService.checkUserPasswordByEmail(login.getEmail());

        // Validate User Password:
        if(!BCrypt.checkpw(login.getPassword(), hashed_password)){
            return new ResponseEntity("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        HashMap map = new HashMap<>();
        User user = userService.getUserDetailsByEmail(login.getEmail());
        // Set User Object:
        map.put("user",user);
        Pharmacie pharmacie = pharmacieService.findPharmacieByUser(user);
        if(pharmacie == null)
            map.put("pharmacie", false);
        else{
            map.put("pharmacie", true);
            map.put("id",pharmacie.getId());
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
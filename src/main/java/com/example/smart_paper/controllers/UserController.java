package com.example.smart_paper.controllers;

import com.example.smart_paper.models.UserModel;
import com.example.smart_paper.models.UserType;
import com.example.smart_paper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder encoder;

    // Registration
    @PostMapping("/register")
    // @RequestParam(required = false) String typeName
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserModel user) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserModel createdUser = service.createUser(user,"parent");
            response.put("message", "User created successfully");
            response.put("user", createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserModel user) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> loginResponse = service.loginUser(user);
            loginResponse.put("message", "User logged in successfully");
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", "Login failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    // Update User Info
    @PutMapping("/update")
    public ResponseEntity<String> updateUserInfo(@RequestParam("id") Long id, @RequestBody UserModel user) {
        try {
            // First, get the existing user
            UserModel existingUser = service.getUserById(id);
            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Check if old password matches
            if (user.getOldPassword() == null || user.getOldPassword().isEmpty() ||
                    !encoder.matches(user.getOldPassword(), existingUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect old password");
            }

            // Update the user
            UserModel updatedUser = service.updateUser(id, user);
            return ResponseEntity.ok("User updated successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed: " + e.getMessage());
        }
    }
}

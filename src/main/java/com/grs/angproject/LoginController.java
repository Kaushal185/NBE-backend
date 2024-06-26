package com.grs.angproject;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grs.angproject.user.UserRepository;
import com.grs.angproject.user.Users;



// @RequestMapping("/api/users")

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<String> login(@RequestBody Users user) {
        Optional<Users> existingUser = userRepository.findByUserId(user.getUserId());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
        	return ResponseEntity.status(HttpStatus.OK).body("SPRING: Login Successful");
        } else {
//            return "Login failed";
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SPRING: Login Failed");
        }
    }

}


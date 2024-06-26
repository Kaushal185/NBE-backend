package com.grs.angproject.user;

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

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<String> login(@RequestBody Users user) {
        Optional<Users> existingUser = userRepository.findByUserId(user.getUserId());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body("SPRING: Login Successful");
        } else {
            // return "Login failed";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SPRING: Login Failed");
        }
    }
//@Autowired
//private JwtUtil jwtUtil;
//
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public ResponseEntity<String> login(@RequestBody Users user) {
//        System.out.println("");
//        Optional<Users> existingUser = userRepository.findByUserId(user.getUserId());
//        System.out.println("api hit");
//        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
//            String token = jwtUtil.generateToken(user.getUserId());
//            return ResponseEntity.status(HttpStatus.OK).body(token);
//        } else {
//            System.out.println("else here");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SPRING: Login Failed");
//        }
//    }

}
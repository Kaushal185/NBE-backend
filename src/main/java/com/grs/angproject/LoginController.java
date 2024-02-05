package com.grs.angproject;

import com.grs.angproject.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.grs.angproject.user.UserRepository;
import com.grs.angproject.user.Users;



// @RequestMapping("/api/users")

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:5000")
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

    // @Autowired
    // private UserRepository userRepository;

    // @GetMapping("/login")
    // public String showLoginForm() {
    //     return "login";
    // }

    // @PostMapping("/login")
    // public String login(String user_id, String password, Model model) {
    //     // Simulate authentication logic
    //     User user = userRepository.findByUser_id(user_id).orElse(null);

    //     if (user != null && user.getPassword().equals(password)) {
    //         // Successful login
    //         return "redirect:/dashboard";
    //     } else {
    //         // Failed login
    //         model.addAttribute("error", "Invalid user_id or password");
    //         return "login";
    //     }
    // }
}


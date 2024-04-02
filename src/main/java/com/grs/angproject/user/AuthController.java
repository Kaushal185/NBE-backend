package com.grs.angproject.user;

import com.grs.angproject.model.JwtRequest;
import com.grs.angproject.model.JwtResponse;
import com.grs.angproject.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

//    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        try {
            // Attempt authentication
            this.doAuthenticate(request.getUsername(), request.getPassword());
    
            // If authentication is successful, generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = this.helper.generateToken(userDetails);
    
            JwtResponse res = new JwtResponse();
            res.setJwtToken(token);
            res.setUsername(userDetails.getUsername());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            // If authentication fails, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password Hello !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}


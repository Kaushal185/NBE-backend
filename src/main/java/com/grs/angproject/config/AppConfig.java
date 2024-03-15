package com.grs.angproject.config;

import com.grs.angproject.user.UserRepository;
import com.grs.angproject.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
class AppConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService() {

        List<Users> list = new ArrayList<>();
        list = userRepository.findAll();
        List<UserDetails> udList= new ArrayList<>();

        for(int i=0;i<list.size();i++){
            UserDetails userDetails = User.builder().
                    username(list.get(i).getUserId())
                    .password(passwordEncoder().encode(list.get(i).getPassword())).roles("ADMIN").
                    build();
            udList.add(userDetails);
        }
        return new InMemoryUserDetailsManager(udList);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
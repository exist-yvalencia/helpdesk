package com.example.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private Environment env;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName() == env.getProperty("spring.security.user.name")){
            return new ResponseEntity<>("USER", HttpStatus.OK);
        }
        return new ResponseEntity<>("ADMIN", HttpStatus.OK);
    }
}

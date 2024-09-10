package com.nathan.backend.controllers;

import com.nathan.backend.config.UserAuthProvider;
import com.nathan.backend.dto.CredentialsDto;
import com.nathan.backend.dto.RegistrationDto;
import com.nathan.backend.entities.User;
import com.nathan.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody CredentialsDto credentials) {
        User user = userService.login(credentials);

        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegistrationDto registration) {
        User user = userService.register(registration);

        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }
}

package com.example.demo.controller;

import com.example.demo.config.JWTCore;
import com.example.demo.model.InputLoginModel;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService service;
    @Autowired
    private JWTCore jwtCore;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public ResponseEntity<?> getInformation(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> postNewUser(@RequestBody InputLoginModel inputLoginModel) {
        User user = new User(inputLoginModel);
        if (service.existsUserByName(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Это имя уже занято!");
        }
        service.addNewUser(user);
        try {
            return ResponseEntity.ok(service.login(user, authenticationManager));
        } catch (Exception _) {}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR, please, enter late");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody InputLoginModel inputLoginModel) {
        User user = new User(inputLoginModel);
        if (!service.existsUserByName(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такого пользователя не существует!");
        }
        User dbUser = service.getUserByName(user.getUsername());
        if (!service.isCorrectPassword(dbUser, user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверный пароль!");
        }
        try {
            return ResponseEntity.ok(service.login(user, authenticationManager));
        } catch (Exception _) {}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR, please, enter late");
    }



}

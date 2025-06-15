package com.example.spcapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spcapi.dtos.LoginRequestDTO;
import com.example.spcapi.services.UserService;

// Outras importações, se necessário

@RestController
public class UserController {

    // Dependências injetadas
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email e senha são obrigatórios.");
        }

        try {
            String token = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

            if (token == null) {
                return ResponseEntity.badRequest().body("Email ou senha incorretos!");
            }

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno no servidor.");
        }
    }
}

package com.example.bookapi.controller;

import com.example.bookapi.model.User;
import com.example.bookapi.repository.UserRepository;
import com.example.bookapi.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    // DTO semplice per il login
    public static class LoginRequest {
        public String username;
        public String password;

        // getters/setters se vuoi
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Autentica username e password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        );

        // Prendi i dettagli utente
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Genera il token JWT
        String jwt = jwtUtils.generateToken(userDetails);

        // Puoi restituire anche info utente, qui solo token
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}

package com.example.bookapi.config;

import com.example.bookapi.model.Role;
import com.example.bookapi.model.User;
import com.example.bookapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class UserInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin1").isEmpty()) {
                User admin = new User("admin1", passwordEncoder.encode("admin1"), Set.of(Role.ADMIN));
                userRepository.save(admin);
                System.out.println(" Utente admin creato con username='admin' e password='admin'");
            }
        };
    }
}

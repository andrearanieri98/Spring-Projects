package com.example.bookapi.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();  // Spring Security convention: roles prefixed with "ROLE_"
    }
}

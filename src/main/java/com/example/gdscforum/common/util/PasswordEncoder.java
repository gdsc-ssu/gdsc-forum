package com.example.gdscforum.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordEncoder {
    @Autowired
    private final BCryptPasswordEncoder customPasswordEncoder;

    public String encode(String password) {
        return customPasswordEncoder.encode(password);
    }

    public boolean matches(String password, String encodedPassword) {
        return customPasswordEncoder.matches(password, encodedPassword);
    }
}
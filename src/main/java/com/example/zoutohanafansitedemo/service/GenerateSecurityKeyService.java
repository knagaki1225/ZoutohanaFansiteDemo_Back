package com.example.zoutohanafansitedemo.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class GenerateSecurityKeyService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static final int length = 8;

    public static String generateSecurityKey() {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}

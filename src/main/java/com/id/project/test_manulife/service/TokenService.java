package com.id.project.test_manulife.service;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {

    // Tempat menyimpan daftar token yang VALID
    private final Set<String> activeTokens = ConcurrentHashMap.newKeySet();

    // Panggil method ini saat berhasil me-generate token baru
    public void saveToken(String token) {
        activeTokens.add(token);
    }

    // Panggil method ini untuk mengecek apakah token ada di memori
    public boolean isTokenValid(String token) {
        return activeTokens.contains(token);
    }
}
package com.udeafood.sevice.auth;

import com.udeafood.DTO.AuthResponse;
import com.udeafood.DTO.LoginRequest;
import com.udeafood.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthResponse register(Usuario usuario) {
        return null;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}

package com.udeafood.controller.auth;

import com.udeafood.DTO.AuthResponse;
import com.udeafood.DTO.LoginRequest;
import com.udeafood.model.Usuario;
import com.udeafood.sevice.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(authService.login(loginRequest));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario usuario){
        return ResponseEntity.ok(authService.register(usuario));
    }
}

package com.udeafood.sevice.auth;

import com.udeafood.DTO.AuthResponse;
import com.udeafood.DTO.LoginRequest;
import com.udeafood.jwt.JwtService;
import com.udeafood.model.Usuario;
import com.udeafood.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsuarioRepository iUsuarioRepository;
    private final JwtService jwtService;

    public AuthResponse register(Usuario usuario) {
        Usuario newUser = new Usuario();
        newUser.setUsuario(usuario.getUsuario());
        newUser.setCorreo(usuario.getCorreo());
        newUser.setClave(usuario.getClave());
        newUser.setNombre(usuario.getNombre());
        newUser.setApellido(usuario.getApellido());
        newUser.setTipoDocumento(usuario.getTipoDocumento());
        newUser.setDocumento(usuario.getDocumento());
        newUser.setCelular(usuario.getCelular());
        newUser.setUbicacion(usuario.getUbicacion());
        newUser.setFoto(usuario.getFoto());
        newUser.setRol(usuario.getRol());
        newUser.setTiendas(usuario.getTiendas());

        iUsuarioRepository.save(newUser);

        return AuthResponse.builder()
                .token(jwtService.getToken(newUser))
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}

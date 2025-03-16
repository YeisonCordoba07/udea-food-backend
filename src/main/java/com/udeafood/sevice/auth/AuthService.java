package com.udeafood.sevice.auth;

import com.udeafood.DTO.AuthResponse;
import com.udeafood.DTO.LoginRequest;
import com.udeafood.DTO.UsuarioDTO;
import com.udeafood.jwt.JwtService;
import com.udeafood.model.Rol;
import com.udeafood.model.Usuario;
import com.udeafood.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsuarioRepository iUsuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public final AuthenticationManager authenticationManager;




    public AuthResponse register(UsuarioDTO usuarioDTO) {
        Usuario newUser = new Usuario();
        newUser.setUsuario(usuarioDTO.getUsuario());
        newUser.setCorreo(usuarioDTO.getCorreo());
        newUser.setClave(passwordEncoder.encode(usuarioDTO.getClave()));
        newUser.setFechaCreacion(new Date());

        newUser.setNombre(usuarioDTO.getNombre());
        newUser.setApellido(usuarioDTO.getApellido());
        newUser.setTipoDocumento(usuarioDTO.getTipoDocumento());
        newUser.setDocumento(usuarioDTO.getDocumento());
        newUser.setCelular(usuarioDTO.getCelular());
        newUser.setUbicacion(usuarioDTO.getUbicacion());
        newUser.setFoto(usuarioDTO.getFoto());

        Rol newRol = new Rol();
        newRol.setIdRol(1);

        newUser.setRol(newRol);


        iUsuarioRepository.save(newUser);

        return AuthResponse.builder()
                .token(jwtService.getToken(newUser))
                .build();
    }


    public AuthResponse login(LoginRequest loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );

        UserDetails userDetails = iUsuarioRepository.findByUsuario(loginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found -- AuthService"));

        String token = jwtService.getToken(userDetails);


        return AuthResponse.builder()
                .token(token)
                .build();
    }
}

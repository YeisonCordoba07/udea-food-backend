package com.udeafood.sevice.auth;

import com.udeafood.DTO.LoginRequest;
import com.udeafood.DTO.UsuarioDTO;
import com.udeafood.DTO.auth.AccountInfo;
import com.udeafood.DTO.auth.AuthResponse;
import com.udeafood.DTO.auth.TiendaInfo;
import com.udeafood.DTO.auth.UsuarioPartialInfo;
import com.udeafood.jwt.JwtService;
import com.udeafood.model.Rol;
import com.udeafood.model.Usuario;
import com.udeafood.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        // Autenticar al usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );

        // Recuperar detalles del usuario
        Usuario usuario = iUsuarioRepository.findByUsuario(loginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado -- AuthService"));

        // Generar token JWT
        String token = jwtService.getToken(usuario);

        // Mapear información parcial del usuario
        UsuarioPartialInfo usuarioInfo = new UsuarioPartialInfo(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getFoto(),
               "usuario"
        );


        // Mapear información de las tiendas asociadas
        List<TiendaInfo> tiendasInfo = usuario.getTiendas().stream()
                .map(tienda -> new TiendaInfo(
                        tienda.getIdTienda(),
                        tienda.getNombre(),
                        tienda.getFoto(),
                        "tienda",
                        tienda.getTipoTienda().name()
                ))
                .toList();


        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUsuario(usuarioInfo);
        accountInfo.setTiendas(tiendasInfo);
        accountInfo.setIdActivo(usuario.getIdUsuario());

        // Construir y devolver AuthResponse
        return AuthResponse.builder()
                .token(token)
                .accountInfo(accountInfo)
                .build();
    }
}

package com.udeafood.DTO.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioPartialInfo {
    private Integer id;
    private String nombre;
    private String foto;
    private String tipoCuenta;
}

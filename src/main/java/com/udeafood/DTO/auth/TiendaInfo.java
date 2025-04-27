package com.udeafood.DTO.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TiendaInfo {
    private Integer id;
    private String nombre;
    private String foto;
    private String tipoCuenta;
    private String tipoTienda;
}

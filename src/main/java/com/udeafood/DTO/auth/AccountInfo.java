package com.udeafood.DTO.auth;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
    private UsuarioPartialInfo usuario;
    private List<TiendaInfo> tiendas;
    private Integer idActivo;
}

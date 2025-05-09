package com.udeafood.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeccionTiendaDTO {
    private Integer idSeccionTienda;
    private String nombre;
    private List<ProductoResponseDTO> productos;
}

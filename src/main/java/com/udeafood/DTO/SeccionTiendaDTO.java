package com.udeafood.DTO;


import lombok.*;

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

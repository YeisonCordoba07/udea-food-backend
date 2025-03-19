package com.udeafood.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean disponibilidad;

    private List<String> imagenes;
    private List<Integer> categorias;
    private Integer idSeccionTienda;

    private Integer idTienda;
}

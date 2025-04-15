package com.udeafood.DTO;

import com.udeafood.model.Categoria;
import com.udeafood.model.ImagenProducto;
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

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean disponibilidad;

    private List<Categoria> categorias;
    private List<ImagenProducto> imagenes;

    private Integer idTienda;

}

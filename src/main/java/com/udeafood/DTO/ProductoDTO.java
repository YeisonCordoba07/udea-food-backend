package com.udeafood.DTO;

import com.udeafood.model.Categoria;
import com.udeafood.model.ImagenProducto;
import com.udeafood.model.SeccionTienda;
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

    private List<ImagenProducto> imagenes;
    private List<Categoria> categorias;
    private SeccionTienda seccionTienda;

    private Integer idTienda;
}

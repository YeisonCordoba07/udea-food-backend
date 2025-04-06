package com.udeafood.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilTiendaDTO {
    private Integer idTienda;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String foto;
    private String portada;
    private Boolean haceDomicilio;
    private String celular;
    private String tipoTienda;
    private BigDecimal calificacion;
    private Integer cantidadCalificaciones;
    private Date fechaCreacion;
    private List<String> categorias;
    private List<SeccionTiendaDTO> secciones;
}


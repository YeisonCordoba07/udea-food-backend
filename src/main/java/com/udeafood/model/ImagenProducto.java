package com.udeafood.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "imagen_producto")
public class ImagenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen_producto")
    private Integer idImagenProducto;

    @Column(name = "enlace_imagen", nullable = false, length = 500)
    private String enlaceImagen;




    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
}


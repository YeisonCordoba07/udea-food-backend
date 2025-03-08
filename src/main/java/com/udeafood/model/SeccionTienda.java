package com.udeafood.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seccion_tienda")
public class SeccionTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSeccionTienda;

    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;




    @OneToMany(mappedBy = "seccionTienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;


    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;


}

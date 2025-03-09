package com.udeafood.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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



    @JsonIgnore
    @OneToMany(mappedBy = "seccionTienda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Producto> productos;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;


}

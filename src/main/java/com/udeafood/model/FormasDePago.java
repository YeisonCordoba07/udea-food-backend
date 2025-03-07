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
@Table(name = "formas_de_pago")
public class FormasDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFormaPago;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;




    @ManyToMany(mappedBy = "formasDePago")
    private List<Tienda> tiendas;

}

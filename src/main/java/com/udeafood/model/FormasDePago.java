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
@Table(name = "formas_de_pago")
public class FormasDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormasDePago;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;




    @JsonIgnore
    @ManyToMany(mappedBy = "formasDePago", fetch = FetchType.LAZY)
    private List<Tienda> tiendas;

}

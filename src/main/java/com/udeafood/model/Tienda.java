package com.udeafood.model;


import com.udeafood.model.util.TipoTienda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tienda")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tienda")
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(length = 400)
    private String ubicacion;

    @Column(length = 500)
    private String foto;

    @Column(length = 500)
    private String portada;

    @Column(name = "hace_domicilio", nullable = false)
    private Boolean haceDomicilio;

    @Column(length = 15)
    private String celular;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tienda", nullable = false)
    private TipoTienda tipoTienda;

    @Column(precision = 3, scale = 2)
    private BigDecimal calificacion;

    @Column(name = "cantidad_calificaciones", columnDefinition = "INT DEFAULT 0")
    private Integer cantidadCalificaciones;

    @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreacion;




    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeccionTienda> secciones;


    @OneToOne(mappedBy = "tienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private HorarioTienda horarioTienda;


    @ManyToMany
    @JoinTable(
            name = "tienda_formas_de_pago",
            joinColumns = @JoinColumn(name = "id_tienda"),
            inverseJoinColumns = @JoinColumn(name = "id_formas_de_pago")
    )
    private List<FormasDePago> formasDePago;


    @ManyToMany
    @JoinTable(
            name = "categoria_tienda",
            joinColumns = @JoinColumn(name = "id_tienda"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;


}

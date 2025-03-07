package com.udeafood.model;
import com.udeafood.model.util.TipoDocumento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "usuario", unique = true, nullable = false, length = 70)
    private String usuario;

    @Column(name = "correo", unique = true, nullable = false, length = 100)
    private String correo;

    @Column(name = "clave", nullable = false, length = 255)
    private String clave;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion = new Date();

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", length = 100)
    private String apellido;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;


    @Column(name = "documento", length = 20)
    private String documento;

    @Column(name = "celular", length = 15)
    private String celular;

    @Column(name = "ubicacion", length = 400)
    private String ubicacion;

    @Column(name = "foto", length = 500)
    private String foto;





    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tienda> tiendas;


}

package com.udeafood.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;




    @JsonIgnore
    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}


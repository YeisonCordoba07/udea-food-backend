package com.udeafood.model;

import com.udeafood.model.util.DiaSemana;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "horario_tienda")
public class HorarioTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_horario_tienda")
    private Integer idHorarioTienda;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia", nullable = false)
    private DiaSemana dia;

    @Column(name = "hora_apertura", nullable = false)
    private String horaApertura;

    @Column(name = "hora_cierre", nullable = false)
    private String horaCierre;

    @Column(name = "notas", length = 400)
    private String notas;




    @OneToOne
    @JoinColumn(name = "id_tienda", nullable = false)
    private Tienda tienda;
}


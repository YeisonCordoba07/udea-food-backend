package com.udeafood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {
    private String nombre;
    private int minSeleccion;
    private int maxSeleccion;
    private List<OpcionIngrediente> opciones;
}

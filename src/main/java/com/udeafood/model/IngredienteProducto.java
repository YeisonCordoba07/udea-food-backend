package com.udeafood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "IngredienteProducto")
public class IngredienteProducto {

    @Id
    private String id;
    private Integer idProducto;
    private Integer idTienda;
    private List<Ingrediente> ingredientes;

}

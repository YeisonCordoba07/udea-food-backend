package com.udeafood.sevice.interfaces;

import com.udeafood.model.Categoria;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> getAll();
    Categoria getById(Integer idCategoria);
}

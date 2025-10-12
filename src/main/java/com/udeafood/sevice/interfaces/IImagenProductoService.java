package com.udeafood.sevice.interfaces;

import com.udeafood.model.ImagenProducto;

import java.util.List;

public interface IImagenProductoService {

    List<ImagenProducto> getAll();
    List<ImagenProducto> getAllByIdProducto(Integer idProducto);
    void save(ImagenProducto imagenProducto);
}

package com.udeafood.sevice.interfaces;

import com.udeafood.DTO.ProductoDTO;
import com.udeafood.DTO.ProductoRequestDTO;

import java.util.List;

public interface IProductoService {
    List<ProductoDTO> getAll();

    List<ProductoDTO> getAllByIdSeccionTienda(Integer idSeccion);

    List<ProductoDTO> getAllByIdTienda(Integer idTienda);

    ProductoDTO getByIdProducto(Integer idProducto);

    List<ProductoDTO> getByNombreCategoria(String categoria);

    List<ProductoDTO> getByNombreProducto(String nombre);

    void save(ProductoRequestDTO productoRequestDTO);

    void delete(Integer id);
}

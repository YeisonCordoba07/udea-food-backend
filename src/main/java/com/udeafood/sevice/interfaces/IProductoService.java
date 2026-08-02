package com.udeafood.sevice.interfaces;

import com.udeafood.DTO.ProductoDTO;
import com.udeafood.DTO.ProductoRequestDTO;
import com.udeafood.DTO.SearchResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductoService {
    List<ProductoDTO> getAll();

    List<ProductoDTO> getAllByIdSeccionTienda(Integer idSeccion);

    List<ProductoDTO> getAllByIdTienda(Integer idTienda);

    ProductoDTO getByIdProducto(Integer idProducto);

    List<ProductoDTO> getByNombreCategoria(String categoria);

    List<ProductoDTO> getByIdCategoria(Integer idCategoria);

    SearchResult<ProductoDTO> getByNombreProducto(String nombre, String buscarEn, String ordenarPor, String tipoOrden, Integer page, Integer size);

    void save(ProductoRequestDTO productoRequestDTO);

    void delete(Integer id);
}

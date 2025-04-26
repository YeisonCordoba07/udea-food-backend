package com.udeafood.mapper;

import com.udeafood.DTO.ProductoDTO;
import com.udeafood.model.Producto;
import com.udeafood.sevice.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductoMapper {
    private final TiendaService tiendaService;

    public ProductoDTO productoToProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(producto.getIdProducto());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setDisponibilidad(producto.getDisponibilidad());
        productoDTO.setCategorias(producto.getCategorias());
        productoDTO.setImagenes(producto.getImagenesProducto());
        productoDTO.setIdTienda(tiendaService.getIdTiendaByIdProducto(producto.getIdProducto()));
        return productoDTO;
    }

    public List<ProductoDTO> listProductoToListProductoDTO(List<Producto> listaProducto){

        if(listaProducto.isEmpty()){
            return Collections.emptyList();
        }
        List<ProductoDTO> listaProductoDTO = new ArrayList<>();
        for(Producto p: listaProducto){
            ProductoDTO productoDTO = productoToProductoDTO(p);
            listaProductoDTO.add(productoDTO);
        }
        return listaProductoDTO;
    }

}

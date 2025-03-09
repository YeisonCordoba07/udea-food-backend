package com.udeafood.sevice;

import com.udeafood.DTO.ProductoConImagenDTO;
import com.udeafood.model.ImagenProducto;
import com.udeafood.model.Producto;
import com.udeafood.repository.IProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductoService {

    private final IProductoRepository iProductoRepository;
    private final ImagenProductoService imagenProductoService;


    public List<Producto> getAll(){
        return iProductoRepository.findAll();
    }


    public List<Producto> getByIdSeccionTienda(Integer idSeccion){
        return iProductoRepository.findBySeccionTienda_IdSeccionTienda(idSeccion);
    }


    public List<Producto> getByIdTienda(Integer idTienda){
        return iProductoRepository.findAllByIdTienda(idTienda);
    }


    public ProductoConImagenDTO getByIdProducto(Integer idProducto){
        Optional<Producto> producto1 = iProductoRepository.findById(idProducto);

        if (producto1.isEmpty()) {
            return null; // Return empty object
        }
        List<ImagenProducto> listImagenProducto = imagenProductoService.getAllByIdProducto(idProducto);

        return getProductoConImagenDTO(producto1, listImagenProducto);
    }

    private static ProductoConImagenDTO getProductoConImagenDTO(Optional<Producto> producto1, List<ImagenProducto> listImagenProducto) {
        ProductoConImagenDTO productoConImagenDTO = new ProductoConImagenDTO();
        productoConImagenDTO.setIdProducto(producto1.get().getIdProducto());
        productoConImagenDTO.setNombre(producto1.get().getNombre());
        productoConImagenDTO.setDescripcion(producto1.get().getDescripcion());
        productoConImagenDTO.setPrecio(producto1.get().getPrecio());
        productoConImagenDTO.setDisponibilidad(producto1.get().getDisponibilidad());
        productoConImagenDTO.setImagenProductos(listImagenProducto);
        return productoConImagenDTO;
    }


    public List<Producto> getByNombreCategoria(String categoria){
        return iProductoRepository.findAllByNombreCategoria(categoria);
    }


}

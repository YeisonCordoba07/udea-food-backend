package com.udeafood.sevice;


import com.udeafood.DTO.PerfilTiendaDTO;
import com.udeafood.DTO.ProductoDTO;
import com.udeafood.DTO.SeccionTiendaDTO;
import com.udeafood.model.Categoria;
import com.udeafood.model.Tienda;
import com.udeafood.repository.ITiendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TiendaService {

    private final ITiendaRepository iTiendaRepository;




    public List<Tienda> getAll(){
        return iTiendaRepository.findAll();
    }


    public Tienda getTiendaById(Integer id){
        return iTiendaRepository.findById(id).orElse(null);
    }

    public PerfilTiendaDTO getPerfilTienda(Integer id) {
        Tienda tienda = iTiendaRepository.findById(id).orElse(null);
        if (tienda == null) return null;

        PerfilTiendaDTO perfiTiendaDTO = new PerfilTiendaDTO();
        perfiTiendaDTO.setIdTienda(tienda.getIdTienda());
        perfiTiendaDTO.setNombre(tienda.getNombre());
        perfiTiendaDTO.setDescripcion(tienda.getDescripcion());
        perfiTiendaDTO.setUbicacion(tienda.getUbicacion());
        perfiTiendaDTO.setFoto(tienda.getFoto());
        perfiTiendaDTO.setPortada(tienda.getPortada());
        perfiTiendaDTO.setHaceDomicilio(tienda.getHaceDomicilio());
        perfiTiendaDTO.setCelular(tienda.getCelular());
        perfiTiendaDTO.setTipoTienda(tienda.getTipoTienda().name());
        perfiTiendaDTO.setCalificacion(tienda.getCalificacion());
        perfiTiendaDTO.setCantidadCalificaciones(tienda.getCantidadCalificaciones());
        perfiTiendaDTO.setFechaCreacion(tienda.getFechaCreacion());

        // Categorías
        perfiTiendaDTO.setCategorias(
                tienda.getCategorias().stream()
                        .map(Categoria::getNombre)
                        .toList()
        );

        // Secciones con productos
        List<SeccionTiendaDTO> seccionDTOs = tienda.getSecciones().stream().map(seccion -> {
            SeccionTiendaDTO sDto = new SeccionTiendaDTO();
            sDto.setIdSeccionTienda(seccion.getIdSeccionTienda());
            sDto.setNombre(seccion.getNombre());
            sDto.setProductos(
                    seccion.getProductos().stream().map(producto -> {
                        ProductoDTO pDto = new ProductoDTO();
                        pDto.setIdProducto(producto.getIdProducto());
                        pDto.setNombre(producto.getNombre());
                        pDto.setDescripcion(producto.getDescripcion());
                        pDto.setPrecio(producto.getPrecio());
                        pDto.setDisponibilidad(producto.getDisponibilidad());
                        pDto.setImagenes(producto.getImagenesProducto().stream()
                                .map(imagen -> imagen.getEnlaceImagen())
                                .toList());
                        pDto.setCategorias(producto.getCategorias().stream()
                                .map(categoria ->{
                                    Categoria c = new Categoria();
                                    c.setIdCategoria(categoria.getIdCategoria());
                                    c.setNombre(categoria.getNombre());
                                    return c;
                                })
                                .toList());
                        pDto.setIdSeccionTienda(producto.getSeccionTienda().getIdSeccionTienda());
                        pDto.setIdTienda(seccion.getTienda().getIdTienda());
                        return pDto;
                    }).toList()
            );
            return sDto;
        }).toList();

        perfiTiendaDTO.setSecciones(seccionDTOs);

        return perfiTiendaDTO;
    }




    public List<Tienda> getTiendaByNombre(String nombre){
        return iTiendaRepository.findByNombre(nombre);
    }


    public List<Tienda> getTiendaPorNombreCategoria(String categoria){
        return iTiendaRepository.findTiendaByNombreCategoria(categoria);
    }


    public List<Tienda> getTiendaByIdUsuario(Integer idUsuario){
        return iTiendaRepository.findAllByUsuario_IdUsuario(idUsuario);
    }
}

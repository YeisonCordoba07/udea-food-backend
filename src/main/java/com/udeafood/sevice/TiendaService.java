package com.udeafood.sevice;


import com.udeafood.DTO.PerfilTiendaDTO;
import com.udeafood.DTO.ProductoResponseDTO;
import com.udeafood.DTO.SearchResult;
import com.udeafood.DTO.SeccionTiendaDTO;
import com.udeafood.model.Categoria;
import com.udeafood.model.Tienda;
import com.udeafood.model.util.TipoTienda;
import com.udeafood.repository.ITiendaRepository;
import com.udeafood.sevice.interfaces.ITiendaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TiendaService implements ITiendaService {

    private final ITiendaRepository iTiendaRepository;



    @Override
    public List<Tienda> getAll(){
        return iTiendaRepository.findAll();
    }


    @Override
    public List<Tienda> getAllByType(String tipoTienda) {

        if(tipoTienda.equalsIgnoreCase("FORMAL")){
            return iTiendaRepository.findAllByTipoTienda(TipoTienda.FORMAL);

        }else if(tipoTienda.equalsIgnoreCase("INFORMAL")){
            return iTiendaRepository.findAllByTipoTienda(TipoTienda.INFORMAL);

        }else{
            throw new IllegalArgumentException("Tipo de tienda no válida");
        }

    }


    @Override
    public Tienda getTiendaById(Integer id){
        return iTiendaRepository.findById(id).orElse(null);
    }


    @Override
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
                        ProductoResponseDTO pDto = new ProductoResponseDTO();
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



    @Override
    public SearchResult<Tienda> getTiendaByNombre(
            String nombre,
            String buscarEn,
            String ordenarPor,
            String tipoOrden,
            String categoria,
            Integer page,
            Integer size
    ){
        Sort.Direction direction = tipoOrden.equalsIgnoreCase("ascendente") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, ordenarPor);
        Pageable pageable = PageRequest.of(page, size, sort);

        TipoTienda tipo = null;

        if (buscarEn != null && !buscarEn.equalsIgnoreCase("todas")) {
            tipo = TipoTienda.valueOf(buscarEn.toUpperCase());
        }
        String nombreFiltro = (nombre == null
            || nombre.isBlank()
            || nombre.equalsIgnoreCase("undefined")
            || nombre.equalsIgnoreCase("null")) ? null : nombre;

        String categoriaFiltro = (categoria == null
            || categoria.isBlank()
            || categoria.equalsIgnoreCase("todas")
            || categoria.equalsIgnoreCase("undefined")
            || categoria.equalsIgnoreCase("null")) ? null : categoria;

        Page<Tienda> pageTienda = iTiendaRepository.findByNombre(nombreFiltro, tipo, pageable, categoriaFiltro);

        return new SearchResult<>(
                pageTienda.getContent(),
                pageTienda.getNumber(),
                pageTienda.getSize(),
                pageTienda.getTotalElements(),
                pageTienda.getTotalPages()
        );
    }


    @Override
    public List<Tienda> getTiendaPorNombreCategoria(String categoria){
        return iTiendaRepository.findTiendaByNombreCategoria(categoria);
    }


    @Override
    public List<Tienda> getTiendaByIdUsuario(Integer idUsuario){
        return iTiendaRepository.findAllByUsuario_IdUsuario(idUsuario);
    }


    @Override
    public Integer getIdTiendaByIdProducto(Integer idProducto){
        Tienda tienda = iTiendaRepository.findBySecciones_Productos_IdProducto(idProducto);
        if(tienda != null) {
            return tienda.getIdTienda();
        }
        return null;
    }


    @Override
    public List<Tienda> getTiendaByNombreUsuario(String nombreUsuario) {
        return iTiendaRepository.findAllByUsuario_Usuario(nombreUsuario);
    }


    @Override
    public boolean existsById(Integer id) {
        return iTiendaRepository.existsById(id);
    }
}

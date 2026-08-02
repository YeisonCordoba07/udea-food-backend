package com.udeafood.sevice.interfaces;

import com.udeafood.DTO.PerfilTiendaDTO;
import com.udeafood.DTO.SearchResult;
import com.udeafood.model.Tienda;

import java.util.List;

public interface ITiendaService {
    List<Tienda> getAll();
    List<Tienda> getAllByType(String tipoTienda);
    Tienda getTiendaById(Integer id);
    PerfilTiendaDTO getPerfilTienda(Integer id);
    SearchResult<Tienda> getTiendaByNombre(
            String nombre,
            String buscarEn,
            String ordenarPor,
            String tipoOrden,
            Integer page,
            Integer size
    );
    List<Tienda> getTiendaPorNombreCategoria(String nombreCategoria);


    List<Tienda> getTiendaByIdUsuario(Integer idUsuario);
    Integer getIdTiendaByIdProducto(Integer idProducto);
    List<Tienda> getTiendaByNombreUsuario(String nombreSeccion);
    boolean existsById(Integer id);
}

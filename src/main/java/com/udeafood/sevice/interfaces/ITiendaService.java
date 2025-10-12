package com.udeafood.sevice.interfaces;

import com.udeafood.DTO.PerfilTiendaDTO;
import com.udeafood.model.Tienda;

import java.util.List;

public interface ITiendaService {
    List<Tienda> getAll();
    List<Tienda> getAllByType(String tipoTienda);
    Tienda getTiendaById(Integer id);
    PerfilTiendaDTO getPerfilTienda(Integer id);
    List<Tienda> getTiendaByNombre(String nombre);
    List<Tienda> getTiendaPorNombreCategoria(String nombreCategoria);


    List<Tienda> getTiendaByIdUsuario(Integer idUsuario);
    Integer getIdTiendaByIdProducto(Integer idProducto);
    List<Tienda> getTiendaByNombreUsuario(String nombreSeccion);
    boolean existsById(Integer id);
}

package com.udeafood.sevice.interfaces;

import com.udeafood.DTO.NuevaSeccionTiendaDTO;
import com.udeafood.model.SeccionTienda;

import java.util.List;

public interface ISeccionTiendaService {
    List<SeccionTienda> getAll();

    List<SeccionTienda> getByTiendaId(Integer idTienda);

    SeccionTienda saveDefault(SeccionTienda defaultSeccionTienda);

    void create(NuevaSeccionTiendaDTO nuevaSeccionTiendaDTO);
}

package com.udeafood.sevice.interfaces;

import com.udeafood.model.HorarioTienda;

import java.util.List;

public interface IHorarioTiendaService {
    List<HorarioTienda> getAll();

    List<HorarioTienda> getByIdTienda(Integer idTienda);
}

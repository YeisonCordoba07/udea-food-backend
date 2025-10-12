package com.udeafood.sevice;

import com.udeafood.model.HorarioTienda;
import com.udeafood.repository.IHorarioTiendaRepository;
import com.udeafood.sevice.interfaces.IHorarioTiendaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class HorarioTiendaService implements IHorarioTiendaService {

    private final IHorarioTiendaRepository iHorarioTiendaRepository;


    @Override
    public List<HorarioTienda> getAll() {
        return iHorarioTiendaRepository.findAll();
    }

    @Override
    public List<HorarioTienda> getByIdTienda(Integer idTienda) {
        return iHorarioTiendaRepository.findAllByTienda_IdTienda(idTienda);
    }
}

package com.udeafood.sevice;

import com.udeafood.model.HorarioTienda;
import com.udeafood.repository.IHorarioTiendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class HorarioTiendaService {

        private final IHorarioTiendaRepository iHorarioTiendaRepository;

        public List<HorarioTienda> getAll(){
            return iHorarioTiendaRepository.findAll();
        }
}

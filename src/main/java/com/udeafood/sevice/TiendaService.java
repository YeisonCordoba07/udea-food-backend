package com.udeafood.sevice;


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

    public List<Tienda> getTiendaByNombre(String nombre){
        return iTiendaRepository.findByNombre(nombre);
    }

    public List<Tienda> getTiendaByCategoria(String categoria){
        return iTiendaRepository.findTiendaByCategoria(categoria);
    }
}

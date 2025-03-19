package com.udeafood.sevice;

import com.udeafood.model.SeccionTienda;
import com.udeafood.repository.ISeccionTiendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeccionTiendaService {

    private final ISeccionTiendaRepository iSeccionTiendaRepository;




    public List<SeccionTienda> getAll(){
        return iSeccionTiendaRepository.findAll();
    }


    public List<SeccionTienda> getByTiendaId(Integer idTienda){
        return iSeccionTiendaRepository.findAllByIdTienda(idTienda);
    }


    public void save(SeccionTienda defaultSeccionTienda) {
        iSeccionTiendaRepository.save(defaultSeccionTienda);
    }
}

package com.udeafood.sevice;


import com.udeafood.model.FormasDePago;
import com.udeafood.repository.IFormasDePagoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class FormasDePagoService {

    private final IFormasDePagoRepository iFormasDePagoRepository;



    public List<FormasDePago> getAll(){
        return iFormasDePagoRepository.findAll();
    }
}

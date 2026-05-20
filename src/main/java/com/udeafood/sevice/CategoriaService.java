package com.udeafood.sevice;

import com.udeafood.model.Categoria;
import com.udeafood.repository.ICategoriaRepository;
import com.udeafood.sevice.interfaces.ICategoriaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository iCategoriaRepository;



    @Override
    public List<Categoria> getAll(){
        return iCategoriaRepository.findAll();
    }
    
    @Override
    public Categoria getById(Integer idCategoria){
        return iCategoriaRepository.findById(idCategoria).orElseThrow();
    }
    
    @Override
    public List<Categoria> getAllByIds(List<Integer> idsCategoria){
        return iCategoriaRepository.findAllById(idsCategoria);
    }
    
}

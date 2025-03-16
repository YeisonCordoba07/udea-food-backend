package com.udeafood.sevice;

import com.udeafood.model.Categoria;
import com.udeafood.repository.ICategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaService {

    private final ICategoriaRepository iCategoriaRepository;




    public List<Categoria> getAll(){
        return iCategoriaRepository.findAll();
    }
}

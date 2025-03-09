package com.udeafood.sevice;

import com.udeafood.model.Producto;
import com.udeafood.repository.IProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoService {

    private final IProductoRepository iProductoRepository;

    public List<Producto> getAll(){
        return iProductoRepository.findAll();
    }


}

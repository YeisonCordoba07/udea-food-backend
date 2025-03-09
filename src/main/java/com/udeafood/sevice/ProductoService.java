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

    public List<Producto> getByIdSeccionTienda(Integer idSeccion){
        return iProductoRepository.findBySeccionTienda(idSeccion);
    }

    public List<Producto> getByIdTienda(Integer idTienda){
        return iProductoRepository.findByIdTienda(idTienda);
    }


}

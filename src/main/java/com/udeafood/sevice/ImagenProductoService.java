package com.udeafood.sevice;

import com.udeafood.model.ImagenProducto;
import com.udeafood.repository.IImagenProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImagenProductoService {

    private final IImagenProductoRepository iImagenProductoRepository;

    public List<ImagenProducto> getAll(){
        return iImagenProductoRepository.findAll();
    }

    public List<ImagenProducto> getAllByIdProducto(Integer idProducto){
        return iImagenProductoRepository.findAllByProducto_IdProducto(idProducto);
    }
}

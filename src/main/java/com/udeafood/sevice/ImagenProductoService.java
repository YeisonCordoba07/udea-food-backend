package com.udeafood.sevice;

import com.udeafood.model.ImagenProducto;
import com.udeafood.repository.IImagenProductoRepository;
import com.udeafood.sevice.interfaces.IImagenProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImagenProductoService implements IImagenProductoService {

    private final IImagenProductoRepository iImagenProductoRepository;



    @Override
    public List<ImagenProducto> getAll(){
        return iImagenProductoRepository.findAll();
    }

    @Override
    public List<ImagenProducto> getAllByIdProducto(Integer idProducto){
        return iImagenProductoRepository.findAllByProducto_IdProducto(idProducto);
    }

    @Override
    public void save(ImagenProducto imagenProducto){
        iImagenProductoRepository.save(imagenProducto);
    }
}

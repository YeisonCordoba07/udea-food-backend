package com.udeafood.sevice.mongodb;

import com.udeafood.model.IngredienteProducto;
import com.udeafood.repository.mongodb.IIngredienteProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class IngredienteProductoService {
    private final IIngredienteProductoRepository iIngredienteProductoRepository;



    public IngredienteProducto guardarIngredientes(IngredienteProducto ingredientes) {
        return iIngredienteProductoRepository.save(ingredientes);
    }

    public Optional<IngredienteProducto> obtenerIngredientesPorProductoId(Integer idProducto) {
        return iIngredienteProductoRepository.findByIdProducto(idProducto);
    }
}

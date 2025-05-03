package com.udeafood.repository.mongodb;

import com.udeafood.model.IngredienteProducto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IIngredienteProductoRepository extends MongoRepository<IngredienteProducto, String> {
    Optional<IngredienteProducto> findByIdProducto(Integer productoId);
}

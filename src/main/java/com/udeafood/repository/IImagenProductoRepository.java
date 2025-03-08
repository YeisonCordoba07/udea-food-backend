package com.udeafood.repository;

import com.udeafood.model.ImagenProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IImagenProductoRepository extends JpaRepository<ImagenProducto, Integer> {
}

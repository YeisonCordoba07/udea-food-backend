package com.udeafood.repository;

import com.udeafood.model.ImagenProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IImagenProductoRepository extends JpaRepository<ImagenProducto, Integer> {

    List<ImagenProducto> findAllByProducto_IdProducto(Integer idProducto);
}

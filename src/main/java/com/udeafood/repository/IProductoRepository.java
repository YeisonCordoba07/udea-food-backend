package com.udeafood.repository;

import com.udeafood.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
}

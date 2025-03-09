package com.udeafood.repository;

import com.udeafood.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p JOIN p.seccionTienda s " +
            "WHERE s.idSeccionTienda = :idSeccionTienda")
    List<Producto> findBySeccionTienda(@PathVariable("idSeccionTienda") Integer idSeccionTienda);


    @Query("SELECT p FROM Producto p JOIN p.seccionTienda s "+
            "JOIN s.tienda t " +
            "WHERE t.idTienda = :idTienda")
    List<Producto> findByIdTienda(@PathVariable("idTienda") Integer idTienda);
}

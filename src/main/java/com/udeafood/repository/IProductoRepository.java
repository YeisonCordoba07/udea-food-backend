package com.udeafood.repository;

import com.udeafood.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    /*@Query("SELECT p FROM Producto p JOIN p.seccionTienda s " +
            "WHERE s.idSeccionTienda = :idSeccionTienda")
    List<Producto> findBySeccionTienda(@PathVariable("idSeccionTienda") Integer idSeccionTienda);*/
    List<Producto> findBySeccionTienda_IdSeccionTienda(Integer idSeccionTienda);


    @Query("SELECT p FROM Producto p JOIN p.seccionTienda s "+
            "JOIN s.tienda t " +
            "WHERE t.idTienda = :idTienda")
    List<Producto> findAllByIdTienda(@PathVariable("idTienda") Integer idTienda);


    @Query("SELECT p FROM Producto p JOIN p.categorias c " +
            "WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :categoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT(:categoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :categoria))")
    List<Producto> findAllByNombreCategoria(@PathVariable("categoria") String categoria);


    @Query("SELECT p FROM Producto p " +
            "WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :producto, '%')) " +
            "   OR LOWER(p.nombre) LIKE LOWER(CONCAT(:producto, '%')) " +
            "   OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :producto))")
    List<Producto> findAllByNombre(@PathVariable("producto") String producto);

}

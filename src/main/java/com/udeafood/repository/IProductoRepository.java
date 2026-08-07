package com.udeafood.repository;

import com.udeafood.model.Producto;
import com.udeafood.model.util.TipoTienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    List<Producto> findAllByIdTienda(@Param("idTienda") Integer idTienda);


    @Query("SELECT p FROM Producto p JOIN p.categorias c " +
            "WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :categoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT(:categoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :categoria))")
    List<Producto> findAllByNombreCategoria(@Param("categoria") String categoria);

    @Query("SELECT p FROM Producto p JOIN p.categorias c " +
            "WHERE c.idCategoria = :idCategoria")
    List<Producto> findAllByIdCategoria(@Param("idCategoria") Integer idCategoria);

    @Query("SELECT DISTINCT p FROM Producto p " +
            "LEFT JOIN p.categorias c " +
            "WHERE (:producto IS NULL" +
            "   OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :producto, '%')) " +
            "   OR LOWER(p.nombre) LIKE LOWER(CONCAT(:producto, '%')) " +
            "   OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :producto))) " +
            "AND (:buscarEn IS NULL OR p.seccionTienda.tienda.tipoTienda = :buscarEn) " +
            "AND (:categoria IS NULL OR :categoria = '' OR LOWER(c.nombre) = LOWER(:categoria))")
    Page<Producto> findAllByNombre(
            @Param("producto") String producto,
            @Param("buscarEn") TipoTienda buscarEn,
            Pageable pageable,
            @Param("categoria") String categoria
    );
}

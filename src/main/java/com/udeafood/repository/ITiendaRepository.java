package com.udeafood.repository;


import com.udeafood.model.Tienda;
import com.udeafood.model.util.TipoTienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITiendaRepository extends JpaRepository<Tienda, Integer> {


    @Query("SELECT t FROM Tienda t " +
            "WHERE (:palabra IS NULL" +
            "   OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :palabra, '%')) " +
            "   OR LOWER(t.nombre) LIKE LOWER(CONCAT(:palabra, '%')) " +
            "   OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :palabra))) " +
            "AND (:buscarEn IS NULL OR t.tipoTienda = :buscarEn) " +
            "AND (:categoria IS NULL OR :categoria = '' OR EXISTS (" +
            "     SELECT 1 FROM t.categorias c " +
            "     WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :categoria, '%'))" +
            "))")
    Page<Tienda> findByNombre(
            @Param("palabra") String palabra,
            @Param("buscarEn") TipoTienda buscarEn,
            Pageable pageable,
            @Param("categoria") String categoria
    );


    @Query("SELECT t FROM Tienda t JOIN t.categorias c " +
            "WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombreCategoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT(:nombreCategoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombreCategoria))")
    List<Tienda> findTiendaByNombreCategoria(@Param("nombreCategoria") String nombreCategoria);


    List<Tienda> findAllByUsuario_IdUsuario(Integer idUsuario);

    @Query("SELECT DISTINCT t FROM Tienda t " +
            "LEFT JOIN FETCH t.secciones " +
            "WHERE t.idTienda = :idTienda")
    Tienda findTiendaWithSecciones(@Param("idTienda") Integer idTienda);

    List <Tienda> findAllByTipoTienda(TipoTienda tipoTienda);

    Tienda findBySecciones_Productos_IdProducto(Integer idProducto);

    Tienda findByUsuario_Usuario(String usuario);

    List<Tienda> findAllByUsuario_Usuario(String usuario);



}

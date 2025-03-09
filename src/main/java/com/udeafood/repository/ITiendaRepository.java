package com.udeafood.repository;


import com.udeafood.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITiendaRepository extends JpaRepository<Tienda, Integer> {


    @Query("SELECT t FROM Tienda t " +
            "WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :palabra, '%')) " +
            "   OR LOWER(t.nombre) LIKE LOWER(CONCAT(:palabra, '%')) " +
            "   OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :palabra))")
    List<Tienda> findByNombre(@Param("palabra") String palabra);


    @Query("SELECT t FROM Tienda t JOIN t.categorias c " +
            "WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :categoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT(:categoria, '%')) " +
            "   OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :categoria))")
    List<Tienda> findTiendaByCategoria(@Param("categoria") String categoria);


    List<Tienda> findAllByUsuario_IdUsuario(Integer idUsuario);

}

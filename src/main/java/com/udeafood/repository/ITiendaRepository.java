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
            "WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :palabra, '%')) " + // Contiene
            "   OR LOWER(t.nombre) LIKE LOWER(CONCAT(:palabra, '%')) " +      // Empieza con
            "   OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :palabra))")        // Termina con
    List<Tienda> findByNombre(@Param("palabra") String palabra);
}

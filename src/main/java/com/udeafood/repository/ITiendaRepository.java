package com.udeafood.repository;


import com.udeafood.model.Tienda;
import com.udeafood.model.util.TipoTienda;
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

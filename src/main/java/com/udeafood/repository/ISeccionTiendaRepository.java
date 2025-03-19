package com.udeafood.repository;

import com.udeafood.model.SeccionTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface ISeccionTiendaRepository extends JpaRepository<SeccionTienda, Integer> {

    @Query("SELECT s FROM SeccionTienda s JOIN s.tienda t "+
        "WHERE t.idTienda = :idTienda"
    )
    List<SeccionTienda> findAllByIdTienda(@PathVariable("idTienda") Integer idTienda);
}

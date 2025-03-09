package com.udeafood.repository;

import com.udeafood.model.SeccionTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISeccionTiendaRepository extends JpaRepository<SeccionTienda, Integer> {

    List<SeccionTienda> findByTiendaId(int tiendaId);
}

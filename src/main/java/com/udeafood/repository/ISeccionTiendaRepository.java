package com.udeafood.repository;

import com.udeafood.model.SeccionTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISeccionTiendaRepository extends JpaRepository<SeccionTienda, Integer> {
}

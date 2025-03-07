package com.udeafood.repository;

import com.udeafood.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITiendaRepository extends JpaRepository<Tienda, Integer> {
}

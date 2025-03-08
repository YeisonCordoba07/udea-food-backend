package com.udeafood.repository;

import com.udeafood.model.HorarioTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IHorarioTiendaRepository extends JpaRepository<HorarioTienda, Integer> {
}

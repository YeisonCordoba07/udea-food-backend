package com.udeafood.repository;

import com.udeafood.model.HorarioTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IHorarioTiendaRepository extends JpaRepository<HorarioTienda, Integer> {

    List<HorarioTienda> findAllByTienda_IdTienda(Integer idTienda);
}

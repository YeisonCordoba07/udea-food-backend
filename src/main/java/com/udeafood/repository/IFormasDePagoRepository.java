package com.udeafood.repository;

import com.udeafood.model.FormasDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormasDePagoRepository extends JpaRepository<FormasDePago, Integer> {
}

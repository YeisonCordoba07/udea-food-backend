package com.udeafood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormasDePagoRepository extends JpaRepository<IFormasDePagoRepository, Integer> {
}

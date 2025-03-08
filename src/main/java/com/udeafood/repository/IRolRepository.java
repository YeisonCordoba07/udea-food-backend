package com.udeafood.repository;

import com.udeafood.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
}

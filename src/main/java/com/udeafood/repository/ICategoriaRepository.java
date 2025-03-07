package com.udeafood.repository;

import com.udeafood.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
}

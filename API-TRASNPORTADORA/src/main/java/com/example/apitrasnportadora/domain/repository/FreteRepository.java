package com.example.apitrasnportadora.domain.repository;

import com.example.apitrasnportadora.domain.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {
}
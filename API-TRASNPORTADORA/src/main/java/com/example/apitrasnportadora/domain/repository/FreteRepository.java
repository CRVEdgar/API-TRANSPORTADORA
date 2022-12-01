package com.example.apitrasnportadora.domain.repository;

import com.example.apitrasnportadora.domain.model.Cidade;
import com.example.apitrasnportadora.domain.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {

    Set<Frete> findByCliente_Id(Long idClient);

//    @Query("SELECT f.cidade, COUNT AS num_fretes FROM Frete f left join fetch CIDADE c ON c.id = f.id GROUP BY  f.cidade ORDER BY num_fretes ASC")
//    Cidade modaCidade();
}
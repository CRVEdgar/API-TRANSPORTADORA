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

//    @Query("SELECT f.cidade, COUNT() AS num_fretes FROM Frete f join Cidade c ON c.id = f.id GROUP BY  f.cidade")
//    Cidade modaCidade();
//
//    @Query("SELECT MAX() FROM (SELECT COUNT() AS num FROM Frete ) f")
//    Cidade modaCidade2();
//
//    @Query("SELECT Cidade, COUNT() from Frete group by Frete.cidade order by count()")
//    Cidade modaCidade3();
}
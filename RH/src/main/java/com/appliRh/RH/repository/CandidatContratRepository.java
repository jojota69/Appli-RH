package com.appliRh.RH.repository;

import com.appliRh.RH.model.CandidatContrat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatContratRepository extends JpaRepository<CandidatContrat, Integer> {

    @EntityGraph(attributePaths = {"typeContrat"})
    List<CandidatContrat> findByCandidatureIdOrderByIdDesc(Integer idCandidature);

    boolean existsByCandidatureId(Integer idCandidature);
}

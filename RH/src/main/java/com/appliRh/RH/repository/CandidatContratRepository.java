package com.appliRh.RH.repository;

import com.appliRh.RH.model.CandidatContrat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatContratRepository extends JpaRepository<CandidatContrat, Integer> {

    @EntityGraph(attributePaths = {"typeContrat"})
    Optional<CandidatContrat> findByCandidatureId(Integer idCandidature);
}

package com.appliRh.RH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.EvaluationCandidat;

public interface EvaluationCandidatRepository extends JpaRepository<EvaluationCandidat, Integer> {

    @EntityGraph(attributePaths = {"entretien", "evaluateur"})
    List<EvaluationCandidat> findByEntretienId(Integer entretienId);
}

package com.appliRh.RH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.Entretien;

public interface EntretienRepository extends JpaRepository<Entretien, Integer> {

    @EntityGraph(attributePaths = {"candidature", "candidature.candidat", "candidature.offre"})
    List<Entretien> findByCandidatureId(Integer candidatureId);

    boolean existsByCandidatureId(Integer candidatureId);

    @Override
    @EntityGraph(attributePaths = {"candidature", "candidature.candidat", "candidature.offre"})
    java.util.Optional<Entretien> findById(Integer id);
}

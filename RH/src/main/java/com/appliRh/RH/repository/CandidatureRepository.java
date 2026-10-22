package com.appliRh.RH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

    @Override
    @EntityGraph(attributePaths = {"candidat", "etat", "offre", "offre.etat"})
    java.util.Optional<Candidature> findById(Integer id);

    @EntityGraph(attributePaths = {"candidat", "etat", "offre", "offre.etat"})
    List<Candidature> findByOffreId(Integer offreId);

    boolean existsByOffreIdAndCandidatId(Integer offreId, Integer candidatId);
}

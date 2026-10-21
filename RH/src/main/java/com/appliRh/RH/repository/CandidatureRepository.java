package com.appliRh.RH.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

    boolean existsByOffreIdAndCandidatId(Integer offreId, Integer candidatId);
}

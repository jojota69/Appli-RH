package com.appliRh.RH.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.EtatCandidature;

public interface EtatCandidatureRepository extends JpaRepository<EtatCandidature, Integer> {
    Optional<EtatCandidature> findByLibelle(String libelle);
}

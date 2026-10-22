package com.appliRh.RH.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.EtatOffre;

public interface EtatOffreRepository extends JpaRepository<EtatOffre, Integer> {

    Optional<EtatOffre> findByLibelleIgnoreCase(String libelle);
}

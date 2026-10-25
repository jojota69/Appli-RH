package com.appliRh.RH.repository;

import com.appliRh.RH.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContratRepository extends JpaRepository<Contrat, Integer> {

    Optional<Contrat> findTopByOrderByIdDesc();
}

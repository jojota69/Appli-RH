package com.appliRh.RH.repository;

import com.appliRh.RH.model.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeContratRepository extends JpaRepository<TypeContrat, Integer> {

	Optional<TypeContrat> findByLibelle(String libelle);
}

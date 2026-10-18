package com.appliRh.RH.repository;

import com.appliRh.RH.model.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PosteRepository extends JpaRepository<Poste, Integer> {

	Optional<Poste> findByLibelle(String libelle);
}

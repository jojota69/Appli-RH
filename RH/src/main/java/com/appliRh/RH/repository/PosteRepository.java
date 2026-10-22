package com.appliRh.RH.repository;

import com.appliRh.RH.model.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosteRepository extends JpaRepository<Poste, Integer> {

	List<Poste> findByLibelleOrderByIdAsc(String libelle);

	boolean existsByLibelle(String libelle);
}

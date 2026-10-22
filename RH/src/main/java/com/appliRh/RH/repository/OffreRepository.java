package com.appliRh.RH.repository;

import com.appliRh.RH.model.Offre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {

    @Override
    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    List<Offre> findAll();

    @Override
    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    Optional<Offre> findById(Integer id);

    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    Optional<Offre> findByBesoinId(Integer demandeId);
}
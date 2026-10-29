package com.appliRh.RH.repository;

import com.appliRh.RH.model.Offre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {

    @Override
    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    List<Offre> findAll();

    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    List<Offre> findByEtatLibelleNotIgnoreCase(String libelle);

    @Query("SELECT o FROM Offre o WHERE LOWER(o.etat.libelle) NOT IN ('fermee', 'termine')")
    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    List<Offre> findAllActives();

    @Override
    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    Optional<Offre> findById(Integer id);

    @EntityGraph(attributePaths = {"besoin", "recruteur", "etat"})
    Optional<Offre> findByBesoinId(Integer demandeId);
}
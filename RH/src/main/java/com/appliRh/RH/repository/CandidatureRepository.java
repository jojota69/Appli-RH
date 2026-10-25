package com.appliRh.RH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

    @Override
    @EntityGraph(attributePaths = {
            "candidat", "etat",
            "offre", "offre.etat",
            "offre.besoin", "offre.besoin.departement"
    })
    java.util.Optional<Candidature> findById(Integer id);

    @EntityGraph(attributePaths = {"candidat", "etat", "offre", "offre.etat"})
    List<Candidature> findByOffreId(Integer offreId);

    @EntityGraph(attributePaths = {
            "candidat", "etat",
            "offre", "offre.etat",
            "offre.besoin", "offre.besoin.departement", "offre.besoin.manager", "offre.besoin.manager.role",
            "offre.besoin.etatDemande", "offre.besoin.typeDemande", "offre.besoin.typeContrat",
            "offre.recruteur", "offre.recruteur.role"
    })
    List<Candidature> findByCandidatId(Integer candidatId);

    @EntityGraph(attributePaths = {"candidat", "etat", "offre", "offre.etat"})
    List<Candidature> findByEtatId(Integer idEtat);

    boolean existsByOffreIdAndCandidatId(Integer offreId, Integer candidatId);
}

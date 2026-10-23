package com.appliRh.RH.repository;

import com.appliRh.RH.model.EmployeeFiche;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeFicheRepository extends JpaRepository<EmployeeFiche, Integer> {

    @Override
    @EntityGraph(attributePaths = {"employee", "poste", "departement", "contrat", "contrat.typeContrat", "contrat.etat"})
    Optional<EmployeeFiche> findById(Integer id);

    @Override
    @EntityGraph(attributePaths = {"employee", "poste", "departement", "contrat", "contrat.typeContrat", "contrat.etat"})
    List<EmployeeFiche> findAll();

    long countByDepartement_IdAndPoste_Id(Integer idDepartement, Integer idPoste);
}

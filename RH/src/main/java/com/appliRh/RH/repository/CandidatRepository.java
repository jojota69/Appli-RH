package com.appliRh.RH.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appliRh.RH.model.Candidat;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {

    Optional<Candidat> findByEmail(String email);
}

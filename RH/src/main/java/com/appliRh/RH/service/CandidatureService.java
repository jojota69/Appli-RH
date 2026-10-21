package com.appliRh.RH.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appliRh.RH.model.Candidat;
import com.appliRh.RH.model.Candidature;
import com.appliRh.RH.model.Offre;
import com.appliRh.RH.repository.CandidatRepository;
import com.appliRh.RH.repository.CandidatureRepository;

@Service
public class CandidatureService {

    private final CandidatRepository candidatRepository;
    private final CandidatureRepository candidatureRepository;

    public CandidatureService(CandidatRepository candidatRepository,
                              CandidatureRepository candidatureRepository) {
        this.candidatRepository = candidatRepository;
        this.candidatureRepository = candidatureRepository;
    }

    @Transactional
    public void enregistrer(Offre offre, String nom, String prenom, String email,
                            String telephone, String cvUrl) {
        Candidat candidat = candidatRepository.findByEmail(email)
                .orElseGet(() -> candidatRepository.save(
                        new Candidat(nom, prenom, email, telephone, cvUrl)));

        if (candidatureRepository.existsByOffreIdAndCandidatId(offre.getId(), candidat.getId())) {
            throw new IllegalStateException("Vous avez déjà candidaté à cette offre.");
        }

        candidatureRepository.save(
                new Candidature(offre, candidat, LocalDate.now(), "Recue"));
    }
}

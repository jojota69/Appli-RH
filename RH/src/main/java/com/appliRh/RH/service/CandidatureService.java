package com.appliRh.RH.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.appliRh.RH.model.Candidat;
import com.appliRh.RH.model.Candidature;
import com.appliRh.RH.model.Offre;
import com.appliRh.RH.repository.CandidatRepository;
import com.appliRh.RH.repository.CandidatureRepository;
import com.appliRh.RH.repository.EtatCandidatureRepository;
import com.appliRh.RH.repository.EntretienRepository;

@Service
public class CandidatureService {

    private final CandidatRepository candidatRepository;
    private final CandidatureRepository candidatureRepository;
    private final EtatCandidatureRepository etatCandidatureRepository;
    private final EntretienRepository entretienRepository;

    public CandidatureService(CandidatRepository candidatRepository,
                              CandidatureRepository candidatureRepository,
                              EtatCandidatureRepository etatCandidatureRepository,
                              EntretienRepository entretienRepository) {
        this.candidatRepository = candidatRepository;
        this.candidatureRepository = candidatureRepository;
        this.etatCandidatureRepository = etatCandidatureRepository;
        this.entretienRepository = entretienRepository;
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

        candidatureRepository.save(new Candidature(
                offre,
                candidat,
                LocalDate.now(),
                trouverEtat("Recue")));
    }

    @Transactional(readOnly = true)
    public List<Candidature> findByOffreId(Integer offreId) {
        return candidatureRepository.findByOffreId(offreId);
    }

    @Transactional(readOnly = true)
    public List<Candidature> findByIdCandidat(Integer candidatId) {
        return candidatureRepository.findByCandidatId(candidatId);
    }

    @Transactional(readOnly = true)
    public Candidature findById(Integer id) {
        return candidatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidature introuvable : " + id));
    }

    @Transactional
    public Candidature changerStatut(Integer id, String statut) {
        Candidature candidature = findById(id);
        candidature.setEtat(trouverEtat(statut));
        return candidatureRepository.save(candidature);
    }

    @Transactional
    public boolean refuserCandidaturesApresQuota(Integer offreId, int effectif) {
        long candidaturesAcceptees =
                candidatureRepository.countByOffreIdAndEtatLibelle(offreId, "Acceptee");
        if (candidaturesAcceptees < effectif) {
            return false;
        }

        com.appliRh.RH.model.EtatCandidature etatPreselectionnee =
                trouverEtat("Preselectionnee");
        com.appliRh.RH.model.EtatCandidature etatRefusee =
                trouverEtat("Refusee");

        List<Candidature> candidatures = candidatureRepository.findByOffreId(offreId);
        candidatures.stream()
                .filter(candidature -> candidature.getEtat().getId()
                        .equals(etatPreselectionnee.getId()))
                .filter(candidature -> entretienRepository.existsByCandidatureId(candidature.getId()))
                .forEach(candidature -> candidature.setEtat(etatRefusee));
        candidatureRepository.saveAll(candidatures);
        return true;
    }

    @Transactional
    public void preselectionnerNonRejetees(Integer offreId) {
        com.appliRh.RH.model.EtatCandidature etatRefusee = trouverEtat("Refusee");
        com.appliRh.RH.model.EtatCandidature etatPreselectionnee = trouverEtat("Preselectionnee");

        List<Candidature> candidatures = candidatureRepository.findByOffreId(offreId);
        candidatures.stream()
                .filter(candidature -> !candidature.getEtat().getId().equals(etatRefusee.getId()))
                .forEach(candidature -> candidature.setEtat(etatPreselectionnee));
        candidatureRepository.saveAll(candidatures);
    }

    @Transactional
    public void preselectionnerRecues(Integer offreId) {
        com.appliRh.RH.model.EtatCandidature etatRecue = trouverEtat("Recue");
        com.appliRh.RH.model.EtatCandidature etatPreselectionnee = trouverEtat("Preselectionnee");

        List<Candidature> candidatures = candidatureRepository.findByOffreId(offreId);
        candidatures.stream()
                .filter(candidature -> candidature.getEtat() != null
                        && candidature.getEtat().getId().equals(etatRecue.getId()))
                .forEach(candidature -> candidature.setEtat(etatPreselectionnee));
        candidatureRepository.saveAll(candidatures);
    }

    @Transactional 
    public List<Candidature> getCandidaturesPreselectionne(){
        return candidatureRepository.findByEtatId(2);
    }

    @Transactional(readOnly = true)
    public List<Candidature> findByEtatLibelle(String libelle) {
        return candidatureRepository.findByEtatLibelleIgnoreCase(libelle);
    }

    private com.appliRh.RH.model.EtatCandidature trouverEtat(String libelle) {
        return etatCandidatureRepository.findByLibelle(libelle)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Etat de candidature introuvable : " + libelle));
    }



}

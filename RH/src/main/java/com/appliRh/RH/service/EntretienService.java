package com.appliRh.RH.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.appliRh.RH.model.Candidature;
import com.appliRh.RH.model.Entretien;
import com.appliRh.RH.repository.EntretienRepository;

@Service
public class EntretienService {

    private final EntretienRepository repository;

    public EntretienService(EntretienRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Entretien> findByCandidatureId(Integer candidatureId) {
        return repository.findByCandidatureId(candidatureId);
    }

    @Transactional(readOnly = true)
    public Entretien findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entretien introuvable : " + id));
    }

    @Transactional
    public Entretien planifier(Candidature candidature, LocalDateTime date,
                               String type, String compteRendu) {
        if (repository.existsByCandidatureId(candidature.getId())) {
            throw new IllegalStateException(
                    "Un entretien est déjà planifié pour cette candidature.");
        }
        return repository.save(new Entretien(candidature, date, type, compteRendu));
    }
}

package com.appliRh.RH.service;

import org.springframework.stereotype.Service;

import com.appliRh.RH.model.EtatOffre;
import com.appliRh.RH.repository.EtatOffreRepository;

@Service
public class EtatOffreService {

    private final EtatOffreRepository repository;

    public EtatOffreService(EtatOffreRepository repository) {
        this.repository = repository;
    }

    public EtatOffre findByLibelle(String libelle) {
        return repository.findByLibelleIgnoreCase(libelle)
                .orElseThrow(() -> new IllegalArgumentException("Etat d'offre introuvable : " + libelle));
    }
}

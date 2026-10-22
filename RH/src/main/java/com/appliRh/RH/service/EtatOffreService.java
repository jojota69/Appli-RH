package com.appliRh.RH.service;

import com.appliRh.RH.model.EtatOffre;
import com.appliRh.RH.repository.EtatOffreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EtatOffreService {

    private final EtatOffreRepository repository;

    public EtatOffreService(EtatOffreRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EtatOffre> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public EtatOffre findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Etat d'offre introuvable : " + id));
    }

    @Transactional
    public EtatOffre create(EtatOffre etatOffre) {
        return repository.save(etatOffre);
    }

    @Transactional
    public EtatOffre update(Integer id, EtatOffre etatOffre) {
        EtatOffre existing = findById(id);
        existing.setLibelle(etatOffre.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

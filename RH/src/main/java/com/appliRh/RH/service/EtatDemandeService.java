package com.appliRh.RH.service;

import com.appliRh.RH.model.EtatDemande;
import com.appliRh.RH.repository.EtatDemandeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EtatDemandeService {

    private final EtatDemandeRepository repository;

    public EtatDemandeService(EtatDemandeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EtatDemande> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public EtatDemande findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Etat de demande introuvable : " + id));
    }

    @Transactional
    public EtatDemande create(EtatDemande etatDemande) {
        return repository.save(etatDemande);
    }

    @Transactional
    public EtatDemande update(Integer id, EtatDemande etatDemande) {
        EtatDemande existing = findById(id);
        existing.setLibelle(etatDemande.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

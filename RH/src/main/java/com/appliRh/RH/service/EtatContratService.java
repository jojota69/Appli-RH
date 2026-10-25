package com.appliRh.RH.service;

import com.appliRh.RH.model.EtatContrat;
import com.appliRh.RH.repository.EtatContratRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EtatContratService {

    private final EtatContratRepository repository;

    public EtatContratService(EtatContratRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EtatContrat> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public EtatContrat findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Etat de contrat introuvable : " + id));
    }

    @Transactional
    public EtatContrat create(EtatContrat etatContrat) {
        return repository.save(etatContrat);
    }

    @Transactional
    public EtatContrat update(Integer id, EtatContrat etatContrat) {
        EtatContrat existing = findById(id);
        existing.setLibelle(etatContrat.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

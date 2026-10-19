package com.appliRh.RH.service;

import com.appliRh.RH.model.TypeContrat;
import com.appliRh.RH.repository.TypeContratRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeContratService {

    private final TypeContratRepository repository;

    public TypeContratService(TypeContratRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TypeContrat> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public TypeContrat findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type de contrat introuvable : " + id));
    }

    @Transactional
    public TypeContrat create(TypeContrat typeContrat) {
        return repository.save(typeContrat);
    }

    @Transactional
    public TypeContrat update(Integer id, TypeContrat typeContrat) {
        TypeContrat existing = findById(id);
        existing.setLibelle(typeContrat.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }

    @Transactional(readOnly = true)
    public TypeContrat findByLibelle(String libelle) {
        return repository.findByLibelle(libelle)
                .orElseThrow(() -> new EntityNotFoundException("Type de contrat introuvable : " + libelle));
    }
}

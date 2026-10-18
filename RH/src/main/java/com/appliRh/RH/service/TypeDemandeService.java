package com.appliRh.RH.service;

import com.appliRh.RH.model.TypeDemande;
import com.appliRh.RH.repository.TypeDemandeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeDemandeService {

    private final TypeDemandeRepository repository;

    public TypeDemandeService(TypeDemandeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TypeDemande> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public TypeDemande findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type de demande introuvable : " + id));
    }

    @Transactional
    public TypeDemande create(TypeDemande typeDemande) {
        return repository.save(typeDemande);
    }

    @Transactional
    public TypeDemande update(Integer id, TypeDemande typeDemande) {
        TypeDemande existing = findById(id);
        existing.setLibelle(typeDemande.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}
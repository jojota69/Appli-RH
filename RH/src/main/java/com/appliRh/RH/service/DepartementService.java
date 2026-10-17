package com.appliRh.RH.service;

import com.appliRh.RH.model.Departement;
import com.appliRh.RH.repository.DepartementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartementService {

    private final DepartementRepository repository;

    public DepartementService(DepartementRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Departement> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Departement findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Departement introuvable : " + id));
    }

    @Transactional
    public Departement create(Departement departement) {
        return repository.save(departement);
    }

    @Transactional
    public Departement update(Integer id, Departement departement) {
        Departement existing = findById(id);
        existing.setLibelle(departement.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

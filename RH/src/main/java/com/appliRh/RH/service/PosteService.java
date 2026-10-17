package com.appliRh.RH.service;

import com.appliRh.RH.model.Poste;
import com.appliRh.RH.repository.PosteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PosteService {

    private final PosteRepository repository;

    public PosteService(PosteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Poste> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Poste findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Poste introuvable : " + id));
    }

    @Transactional
    public Poste create(Poste poste) {
        return repository.save(poste);
    }

    @Transactional
    public Poste update(Integer id, Poste poste) {
        Poste existing = findById(id);
        existing.setLibelle(poste.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

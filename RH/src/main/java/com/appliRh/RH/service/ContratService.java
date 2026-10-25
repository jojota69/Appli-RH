package com.appliRh.RH.service;

import com.appliRh.RH.model.Contrat;
import com.appliRh.RH.repository.ContratRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContratService {

    private final ContratRepository repository;

    public ContratService(ContratRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Contrat> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Contrat findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable : " + id));
    }

    @Transactional(readOnly = true)
    public Contrat findLast() {
        return repository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new EntityNotFoundException("Aucun contrat enregistré"));
    }

    @Transactional
    public Contrat create(Contrat contrat) {
        return repository.save(contrat);
    }

    @Transactional
    public Contrat update(Integer id, Contrat contrat) {
        Contrat existing = findById(id);
        existing.setTypeContrat(contrat.getTypeContrat());
        existing.setEtat(contrat.getEtat());
        existing.setSalaire(contrat.getSalaire());
        existing.setDateDebut(contrat.getDateDebut());
        existing.setDateFin(contrat.getDateFin());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

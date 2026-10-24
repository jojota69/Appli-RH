package com.appliRh.RH.service;

import com.appliRh.RH.model.CandidatContrat;
import com.appliRh.RH.repository.CandidatContratRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidatContratService {

    private final CandidatContratRepository repository;

    public CandidatContratService(CandidatContratRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CandidatContrat> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public CandidatContrat findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Contrat candidat introuvable : " + id));
    }

    @Transactional
    public CandidatContrat create(CandidatContrat candidatContrat) {
        return repository.save(candidatContrat);
    }

    @Transactional
    public CandidatContrat update(Integer id, CandidatContrat candidatContrat) {
        CandidatContrat existing = findById(id);
        existing.setCandidature(candidatContrat.getCandidature());
        existing.setTypeContrat(candidatContrat.getTypeContrat());
        existing.setDateDebut(candidatContrat.getDateDebut());
        existing.setDateFin(candidatContrat.getDateFin());
        existing.setSalaire(candidatContrat.getSalaire());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

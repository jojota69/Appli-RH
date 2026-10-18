package com.appliRh.RH.service;

import com.appliRh.RH.model.DemandeBesoin;
import com.appliRh.RH.repository.DemandeBesoinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemandeBesoinService {

    private final DemandeBesoinRepository repository;

    public DemandeBesoinService(DemandeBesoinRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<DemandeBesoin> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public DemandeBesoin findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demande de besoin introuvable : " + id));
    }

    @Transactional(readOnly = true)
    public List<DemandeBesoin> getDemandeEnAttente(){
        return repository.findAll().stream()
                                .filter(demande -> demande.getEtatDemande() != null
                                                && demande.getEtatDemande().getId().equals(1))
                                .toList();
    }

    @Transactional
    public DemandeBesoin create(DemandeBesoin demandeBesoin) {
        return repository.save(demandeBesoin);
    }

    @Transactional
    public DemandeBesoin update(Integer id, DemandeBesoin demandeBesoin) {
        DemandeBesoin existing = findById(id);
        existing.setPoste(demandeBesoin.getPoste());
        existing.setDepartement(demandeBesoin.getDepartement());
        existing.setManager(demandeBesoin.getManager());
        existing.setEffectif(demandeBesoin.getEffectif());
        existing.setMotif(demandeBesoin.getMotif());
        existing.setBudget(demandeBesoin.getBudget());
        existing.setDateSouhaitee(demandeBesoin.getDateSouhaitee());
        existing.setEtatDemande(demandeBesoin.getEtatDemande());
        existing.setTypeDemande(demandeBesoin.getTypeDemande());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

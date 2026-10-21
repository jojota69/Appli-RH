package com.appliRh.RH.service;

import com.appliRh.RH.model.DemandeBesoin;
import com.appliRh.RH.repository.DemandeBesoinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;

@Service
public class DemandeBesoinService {

    private final DemandeBesoinRepository repository;
    private final EtatDemandeService etatDemandeService;

    public DemandeBesoinService(DemandeBesoinRepository repository, EtatDemandeService etatDemandeService) {
        this.repository = repository;
        this.etatDemandeService = etatDemandeService;
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
    public List<DemandeBesoin> getDemandeEnAttente(Integer id){
        return repository.findByDepartementAndEtat(id, 3);
    }

    @Transactional(readOnly = true)
    public List<DemandeBesoin> getToutesDemandesEnAttente() {
        return repository.findByEtat(3);
    }

    @Transactional(readOnly = true)
    public List<DemandeBesoin> getToutesDemandesEnCoursOuValideRh() {
        return repository.findByEtatsLibelles(List.of(
                "en cours de traitement",
                "valide rh"));
    }

    @Transactional(readOnly = true)
    public List<DemandeBesoin> DepartementEtat(Integer idDepartement, Integer idEtat) {
        return repository.findByDepartementAndEtat(idDepartement, idEtat);
    }

    @Transactional(readOnly = true)
    public BigDecimal getBudgetPoste(Integer idDepartement, String intitulePoste) {
        return repository.sumBudgetByDepartementAndPoste(idDepartement, intitulePoste);
    }

    @Transactional(readOnly = true)
    public BigDecimal getBudgetDepartement(Integer idDepartement) {
        return repository.sumBudgetByDepartement(idDepartement);
    }

    @Transactional
    public DemandeBesoin create(DemandeBesoin demandeBesoin) {
        return repository.save(demandeBesoin);
    }

    @Transactional
    public List<DemandeBesoin> createAll(List<DemandeBesoin> demandes) {
        return repository.saveAll(demandes);
    }

    @Transactional
    public DemandeBesoin update(Integer id, DemandeBesoin demandeBesoin) {
        DemandeBesoin existing = findById(id);
        existing.setIntitulePoste(demandeBesoin.getIntitulePoste());
        existing.setDepartement(demandeBesoin.getDepartement());
        existing.setManager(demandeBesoin.getManager());
        existing.setEffectif(demandeBesoin.getEffectif());
        existing.setMotif(demandeBesoin.getMotif());
        existing.setBudget(demandeBesoin.getBudget());
        existing.setDateSouhaitee(demandeBesoin.getDateSouhaitee());
        existing.setEtatDemande(demandeBesoin.getEtatDemande());
        existing.setTypeDemande(demandeBesoin.getTypeDemande());
        existing.setTypeContrat(demandeBesoin.getTypeContrat());
        return repository.save(existing);
    }

    @Transactional
    public DemandeBesoin changerEtat(Integer id, Integer idEtat) {
        DemandeBesoin demande = findById(id);
        demande.setEtatDemande(etatDemandeService.findById(idEtat));
        return repository.save(demande);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

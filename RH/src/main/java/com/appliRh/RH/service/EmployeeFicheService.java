package com.appliRh.RH.service;

import com.appliRh.RH.model.EmployeeFiche;
import com.appliRh.RH.repository.EmployeeFicheRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeFicheService {

    private final EmployeeFicheRepository repository;

    public EmployeeFicheService(EmployeeFicheRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EmployeeFiche> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public EmployeeFiche findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fiche employe introuvable : " + id));
    }

    @Transactional
    public EmployeeFiche create(EmployeeFiche employeeFiche) {
        return repository.save(employeeFiche);
    }

    @Transactional
    public EmployeeFiche update(Integer id, EmployeeFiche employeeFiche) {
        EmployeeFiche existing = findById(id);
        existing.setEmployee(employeeFiche.getEmployee());
        existing.setPoste(employeeFiche.getPoste());
        existing.setDepartement(employeeFiche.getDepartement());
        existing.setTypeContrat(employeeFiche.getTypeContrat());
        existing.setSalaire(employeeFiche.getSalaire());
        existing.setDateNaissance(employeeFiche.getDateNaissance());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }

    @Transactional(readOnly = true)
    public long nombre(Integer idDepartement, Integer idPoste) {
        return repository.countByDepartement_IdAndPoste_Id(idDepartement, idPoste);
    }
}

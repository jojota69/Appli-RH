package com.appliRh.RH.service;

import com.appliRh.RH.model.BudgetAlloue;
import com.appliRh.RH.model.Candidat;
import com.appliRh.RH.repository.BudgetAlloueRepository;
import com.appliRh.RH.repository.CandidatRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;

@Service
public class CandidatService {

    private final CandidatRepository repository;

    public CandidatService(CandidatRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Candidat> findAll() {
        return repository.findAll();
    }

    

}

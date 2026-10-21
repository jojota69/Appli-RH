package com.appliRh.RH.service;

import com.appliRh.RH.model.BudgetAlloue;
import com.appliRh.RH.repository.BudgetAlloueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;

@Service
public class BudgetAlloueService {

    private final BudgetAlloueRepository repository;

    public BudgetAlloueService(BudgetAlloueRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<BudgetAlloue> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public BigDecimal getBudgetTotal() {
        return repository.sumValeur();
    }

    @Transactional(readOnly = true)
    public BudgetAlloue findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget alloué introuvable : " + id));
    }

    @Transactional
    public BudgetAlloue create(BudgetAlloue budgetAlloue) {
        return repository.save(budgetAlloue);
    }

    @Transactional
    public BudgetAlloue update(Integer id, BudgetAlloue budgetAlloue) {
        BudgetAlloue existing = findById(id);
        existing.setValeur(budgetAlloue.getValeur());
        existing.setMois(budgetAlloue.getMois());
        existing.setAnnee(budgetAlloue.getAnnee());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}

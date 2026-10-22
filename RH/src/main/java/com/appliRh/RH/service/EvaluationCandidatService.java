package com.appliRh.RH.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appliRh.RH.model.Employee;
import com.appliRh.RH.model.Entretien;
import com.appliRh.RH.model.EvaluationCandidat;
import com.appliRh.RH.repository.EvaluationCandidatRepository;

@Service
public class EvaluationCandidatService {

    private final EvaluationCandidatRepository repository;

    public EvaluationCandidatService(EvaluationCandidatRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EvaluationCandidat> findByEntretienId(Integer entretienId) {
        return repository.findByEntretienId(entretienId);
    }

    @Transactional
    public EvaluationCandidat evaluer(Entretien entretien, Employee evaluateur,
                                      BigDecimal note, String commentaire) {
        return repository.save(new EvaluationCandidat(
                entretien, evaluateur, note, commentaire, LocalDate.now()));
    }
}

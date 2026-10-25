package com.appliRh.RH.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.appliRh.RH.model.Candidature;
import com.appliRh.RH.service.CandidatureService;
import com.appliRh.RH.service.CandidatService;
import com.appliRh.RH.model.Candidat;
import com.appliRh.RH.model.Employee;
import com.appliRh.RH.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CandidatureService candidatureService;
    private final CandidatService candidatService;
    private final EmployeeService employeeService;

    public ApiController(CandidatureService candidatureService, CandidatService candidatService, EmployeeService employeeService) {
        this.candidatureService = candidatureService;
        this.candidatService = candidatService;
        this.employeeService = employeeService;
    }

    @GetMapping("/propositions/{email}")
    public List<Candidature> getPropositions(@PathVariable String email) {
        try {
            Employee employee = employeeService.findByEmail(email);
            if (employee != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'email fourni correspond à un employé, pas à un candidat.");
            }
        } catch (Exception e) {
            
        }
        Candidat candidat = candidatService.findByEmail(email);
        if (candidat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidat introuvable avec l'email : " + email);
        }
        List<Candidature> propositions = candidatureService.findByIdCandidat(candidat.getId());
        List<Candidature> filteredPropositions = propositions.stream()
                .filter(candidature -> candidature.getEtat().getLibelle().equals("Proposee"))
                .toList();
        return filteredPropositions;
    }
}

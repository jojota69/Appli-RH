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

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CandidatureService candidatureService;
    private final CandidatService candidatService;

    public ApiController(CandidatureService candidatureService, CandidatService candidatService) {
        this.candidatureService = candidatureService;
        this.candidatService = candidatService;
    }

    @GetMapping("/propositions/{email}")
    public List<Candidature> getPropositions(@PathVariable String email) {
        Candidat candidat = candidatService.findByEmail(email);
        if (candidat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidat introuvable avec l'email : " + email);
        }
        
        return candidatureService.findByIdCandidat(candidat.getId());
    }
}

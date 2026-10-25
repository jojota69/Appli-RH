package com.appliRh.RH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;

import com.appliRh.RH.model.Candidature;
import com.appliRh.RH.service.CandidatureService;
import com.appliRh.RH.service.CandidatContratService;
import com.appliRh.RH.model.CandidatContrat;

@Controller 
public class EmbaucheController {
    
    private final CandidatureService candidatureService;
    private final CandidatContratService candidatContratService;

    public EmbaucheController(CandidatureService candidatureService, CandidatContratService candidatContratService) {
        this.candidatureService = candidatureService;
        this.candidatContratService = candidatContratService;
    }

    @GetMapping("/embauche")
    public String embauche(@RequestParam(defaultValue = "dashboard") String section,
                       HttpSession session, Model model) {

        String selectedSection = switch (section) { 
            case "listeOffresEmbauche" -> section;
            default -> "listeOffresEmbauche";
        };

        model.addAttribute("section", selectedSection);

        return "pages/embauche";
    }

    @GetMapping ("/contrat/{id}")
    public String contrat(@PathVariable Integer id, Model model) {
        Candidature candidature = candidatureService.findById(id);
        model.addAttribute("candidat", candidature.getCandidat());
        CandidatContrat contrat = candidatContratService.findByIdCandidature(candidature.getId());
        model.addAttribute("contrat", contrat);
        return "pages/contrat";
    }
}


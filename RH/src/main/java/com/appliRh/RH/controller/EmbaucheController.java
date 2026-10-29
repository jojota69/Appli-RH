package com.appliRh.RH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.appliRh.RH.model.Candidature;
import com.appliRh.RH.model.Offre;
import com.appliRh.RH.service.CandidatureService;
import com.appliRh.RH.service.CandidatContratService;
import com.appliRh.RH.model.CandidatContrat;
import com.appliRh.RH.model.Employee;
import com.appliRh.RH.service.EmployeeService;
import com.appliRh.RH.service.RoleService;
import com.appliRh.RH.model.Poste;
import com.appliRh.RH.service.PosteService;
import com.appliRh.RH.model.Departement;
import com.appliRh.RH.model.Contrat;
import com.appliRh.RH.service.ContratService;
import com.appliRh.RH.service.TypeContratService;
import com.appliRh.RH.service.EtatContratService;
import com.appliRh.RH.model.EmployeeFiche;
import com.appliRh.RH.service.EmployeeFicheService;
import com.appliRh.RH.service.OffreService;
import com.appliRh.RH.service.EtatOffreService;

@Controller 
public class EmbaucheController {
    
    private final CandidatureService candidatureService;
    private final CandidatContratService candidatContratService;
    private final EmployeeService employeeService;
    private final RoleService roleService;
    private final PosteService posteService;
    private final TypeContratService typeContratService;
    private final EtatContratService etatContratService;
    private final EmployeeFicheService employeeFicheService;
    private final ContratService contratService;
    private final OffreService offreService;
    private final EtatOffreService etatOffreService;

    public EmbaucheController(CandidatureService candidatureService, CandidatContratService candidatContratService, EmployeeService employeeService, RoleService roleService, PosteService posteService, ContratService contratService, TypeContratService typeContratService, EtatContratService etatContratService, EmployeeFicheService employeeFicheService, OffreService offreService, EtatOffreService etatOffreService) {
        this.candidatureService = candidatureService;
        this.candidatContratService = candidatContratService;
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.posteService = posteService;
        this.contratService = contratService;
        this.typeContratService = typeContratService;
        this.etatContratService = etatContratService;
        this.employeeFicheService = employeeFicheService;
        this.offreService = offreService;
        this.etatOffreService = etatOffreService;
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
        model.addAttribute("candidature", candidature);
        return "pages/contrat";
    }

    @PostMapping("/contrat/enregistrer")
    public String enregistrerContrat(@RequestParam Integer idCandidature,
                                     @RequestParam String nom,
                                     @RequestParam String prenom,
                                     @RequestParam String telephone,
                                     @RequestParam String email,
                                     @RequestParam String typeContrat,
                                     @RequestParam LocalDate dateDebut,
                                     @RequestParam(required = false) LocalDate dateFin,
                                     @RequestParam BigDecimal salaire) {
        Employee employee = new Employee(nom, prenom, email, roleService.findById(5), "password123");
        employeeService.create(employee);
        Candidature candidature = candidatureService.findById(idCandidature);
        String libellePoste = candidature.getOffre().getTitre();
        Poste poste = posteService.existsByLibelle(libellePoste)
                ? posteService.findByLibelle(libellePoste)
                : posteService.create(new Poste(libellePoste));
        Contrat contrat = new Contrat(typeContratService.findByLibelle(typeContrat), etatContratService.findById(1), salaire, dateDebut, dateFin);
        contratService.create(contrat);
        Departement departement = candidature.getOffre().getBesoin().getDepartement();
        EmployeeFiche employeeFiche = new EmployeeFiche(employee, poste, departement, contrat, java.time.LocalDate.now());
        employeeFicheService.create(employeeFiche);
        candidatureService.changerStatut(idCandidature, "Acceptee");
        boolean quotaAtteint = candidatureService.refuserCandidaturesApresQuota(
                candidature.getOffre().getId(),
                candidature.getOffre().getBesoin().getEffectif());
        if (quotaAtteint) {
            Offre offre = candidature.getOffre();
            offre.setDateCloture(LocalDate.now());
            offre.setEtat(etatOffreService.findByLibelle("Termine"));
            offreService.save(offre);
        }
        return "redirect:/embauche?section=listeOffresEmbauche";
    }
}

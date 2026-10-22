package com.appliRh.RH.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appliRh.RH.annotation.PareFeu;
import com.appliRh.RH.model.Employee;
import com.appliRh.RH.model.Offre;
import com.appliRh.RH.service.EmployeeService;
import com.appliRh.RH.service.CandidatureService;
import com.appliRh.RH.service.OffreService;
import com.appliRh.RH.service.EntretienService;
import com.appliRh.RH.service.EvaluationCandidatService;
import com.appliRh.RH.model.DemandeBesoin;
import com.appliRh.RH.service.DemandeBesoinService;
import com.appliRh.RH.service.EtatOffreService;
import com.appliRh.RH.model.Candidature;
import com.appliRh.RH.model.Entretien;

import jakarta.servlet.http.HttpSession;

@Controller
public class OffreController {

	private final EmployeeService employeeService;
    private final OffreService offreService;
    private final CandidatureService candidatureService;
    private final EntretienService entretienService;
    private final EvaluationCandidatService evaluationCandidatService;
    private final DemandeBesoinService demandeBesoinService;
    private final EtatOffreService etatOffreService;

    public OffreController(EmployeeService employeeService, OffreService offreService,
                           CandidatureService candidatureService,
                           EntretienService entretienService,
                           EvaluationCandidatService evaluationCandidatService,
                           DemandeBesoinService demandeBesoinService,
                           EtatOffreService etatOffreService) {
		this.employeeService = employeeService;
		this.offreService = offreService;
        this.candidatureService = candidatureService;
        this.entretienService = entretienService;
        this.evaluationCandidatService = evaluationCandidatService;
        this.demandeBesoinService = demandeBesoinService;
        this.etatOffreService = etatOffreService;
	}

    @GetMapping("/")
    public String offresDisponibles(Model model) {
        model.addAttribute("listeOffres", offreService.findAll());
        return "index";
    }

    @GetMapping("/offres-disponibles/{id}")
    public String detailOffre(@PathVariable Integer id,
                              @RequestParam(required = false) String success,
                              @RequestParam(required = false) String error,
                              Model model) {
        Offre offre = offreService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offre introuvable : " + id));
        model.addAttribute("offre", offre);
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        return "offre-detail";
    }

    @PostMapping("/offres-disponibles/{id}/candidature")
    public String candidater(@PathVariable Integer id,
                             @RequestParam String nom,
                             @RequestParam String prenom,
                             @RequestParam String email,
                             @RequestParam String telephone,
                             @RequestParam(required = false, defaultValue = "") String cvUrl,
                             RedirectAttributes redirectAttributes) {
        Offre offre = offreService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offre introuvable : " + id));
        try {
            candidatureService.enregistrer(offre, nom, prenom, email, telephone, cvUrl);
            redirectAttributes.addAttribute("success", "Votre candidature a bien été envoyée.");
        } catch (IllegalStateException exception) {
            redirectAttributes.addAttribute("error", exception.getMessage());
        }
        return "redirect:/offres-disponibles/" + id;
    }

    

	@GetMapping("/offres")
	@PareFeu(roles = "Recruteur")
    public String offres(@RequestParam(defaultValue = "offres") String section,
            Model model, HttpSession session) {
		Employee employee = employeeService.findById((Integer) session.getAttribute("employeeId"));
        String selectedSection = switch (section) {
            case "listeOffres" -> section;
            default -> "listeOffres";
        };

        model.addAttribute("employeeConnecte", employee);
        model.addAttribute("section", selectedSection);

        List<Offre> listeOffres = offreService.findAll();
        model.addAttribute("listeOffres", listeOffres);
        List<DemandeBesoin> demandesValidees = demandeBesoinService.findAll().stream()
            .filter(demande -> demande.getEtatDemande() != null
                && ("Valide".equalsIgnoreCase(demande.getEtatDemande().getLibelle())
                || "Valide RH".equalsIgnoreCase(demande.getEtatDemande().getLibelle())))
            .toList();
        model.addAttribute("demandesValidees", demandesValidees);

		return "pages/offres";
	}

    @GetMapping("/offres/{offreId}/candidatures")
    @PareFeu(roles = "Recruteur")
    public String suiviCandidatures(@PathVariable Integer offreId,
                                    Model model, HttpSession session) {
        Employee employee = employeeService.findById((Integer) session.getAttribute("employeeId"));
        Offre offre = offreService.findById(offreId)
                .orElseThrow(() -> new IllegalArgumentException("Offre introuvable : " + offreId));
        List<Candidature> candidatures = candidatureService.findByOffreId(offreId);
        Map<Integer, List<Entretien>> entretiensParCandidature = new HashMap<>();
        Map<Integer, List<com.appliRh.RH.model.EvaluationCandidat>> evaluationsParEntretien = new HashMap<>();
        for (Candidature candidature : candidatures) {
            List<Entretien> entretiens = entretienService.findByCandidatureId(candidature.getId());
            entretiensParCandidature.put(candidature.getId(), entretiens);
            for (Entretien entretien : entretiens) {
                evaluationsParEntretien.put(entretien.getId(),
                        evaluationCandidatService.findByEntretienId(entretien.getId()));
            }
        }
        model.addAttribute("employeeConnecte", employee);
        model.addAttribute("section", "listeOffres");
        model.addAttribute("offreSelectionnee", offre);
        model.addAttribute("listeCandidatures", candidatures);
        model.addAttribute("entretiensParCandidature", entretiensParCandidature);
        model.addAttribute("evaluationsParEntretien", evaluationsParEntretien);
        return "pages/suivi-candidatures";
    }

    @PostMapping("/offres/creer")
    @PareFeu(roles = "Recruteur")
    public String creerOffre(@RequestParam Integer demandeId,
                 @RequestParam String titre,
                 @RequestParam String description,
                 @RequestParam(required = false) String dateCloture,
                 HttpSession session) {
    DemandeBesoin demande = demandeBesoinService.findById(demandeId);
    Employee recruteur = employeeService.findById((Integer) session.getAttribute("employeeId"));
    LocalDate cloture = dateCloture == null || dateCloture.isBlank()
        ? null : LocalDate.parse(dateCloture);
    Offre offre = new Offre(demande, recruteur, titre, description,
        LocalDate.now(), cloture, etatOffreService.findByLibelle("Publiee"));
    offreService.save(offre);
    return "redirect:/offres";
    }

    @PostMapping("/offres/{offreId}/candidatures/{candidatureId}/statut")
    @PareFeu(roles = "Recruteur")
    public String changerStatutCandidature(@PathVariable Integer offreId,
                                           @PathVariable Integer candidatureId,
                                           @RequestParam String statut) {
        candidatureService.changerStatut(candidatureId, statut);
        return "redirect:/offres/" + offreId + "/candidatures";
    }

    @PostMapping("/candidatures/{candidatureId}/entretiens")
    @PareFeu(roles = "Recruteur")
    public String planifierEntretien(@PathVariable Integer candidatureId,
                                     @RequestParam String dateEntretien,
                                     @RequestParam String typeEntretien,
                                     @RequestParam(required = false, defaultValue = "") String compteRendu) {
        Candidature candidature = candidatureService.findById(candidatureId);
        entretienService.planifier(candidature, LocalDateTime.parse(dateEntretien),
                typeEntretien, compteRendu);
        return "redirect:/offres/" + candidature.getOffre().getId() + "/candidatures";
    }

    @PostMapping("/entretiens/{entretienId}/evaluations")
    @PareFeu(roles = "Recruteur")
    public String evaluerCandidat(@PathVariable Integer entretienId,
                                  @RequestParam BigDecimal note,
                                  @RequestParam String commentaire,
                                  HttpSession session) {
        Entretien entretien = entretienService.findById(entretienId);
        Employee evaluateur = employeeService.findById((Integer) session.getAttribute("employeeId"));
        evaluationCandidatService.evaluer(entretien, evaluateur, note, commentaire);
        return "redirect:/offres/" + entretien.getCandidature().getOffre().getId() + "/candidatures";
    }
}

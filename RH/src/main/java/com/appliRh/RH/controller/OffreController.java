package com.appliRh.RH.controller;

import java.util.List;

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

import jakarta.servlet.http.HttpSession;

@Controller
public class OffreController {

	private final EmployeeService employeeService;
    private final OffreService offreService;
    private final CandidatureService candidatureService;

    public OffreController(EmployeeService employeeService, OffreService offreService,
                           CandidatureService candidatureService) {
		this.employeeService = employeeService;
		this.offreService = offreService;
        this.candidatureService = candidatureService;
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


		return "pages/offres";
	}
}

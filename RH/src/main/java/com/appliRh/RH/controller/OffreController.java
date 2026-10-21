package com.appliRh.RH.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.annotation.PareFeu;
import com.appliRh.RH.model.Employee;
import com.appliRh.RH.model.Offre;
import com.appliRh.RH.service.EmployeeService;
import com.appliRh.RH.service.OffreService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OffreController {

	private final EmployeeService employeeService;
    private final OffreService offreService;

	public OffreController(EmployeeService employeeService, OffreService offreService) {
		this.employeeService = employeeService;
		this.offreService = offreService;
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

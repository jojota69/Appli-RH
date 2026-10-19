package com.appliRh.RH.controller;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.model.Employee;
import com.appliRh.RH.annotation.PareFeu;
import com.appliRh.RH.service.EmployeeService;
import com.appliRh.RH.service.PosteService;
import com.appliRh.RH.model.Poste;
import com.appliRh.RH.model.DemandeBesoin;
import com.appliRh.RH.service.DemandeBesoinService;
import com.appliRh.RH.service.EtatDemandeService;
import com.appliRh.RH.service.TypeDemandeService;

import jakarta.servlet.http.HttpSession;
import jakarta.persistence.EntityNotFoundException;

@Controller
public class HomeController {

    private final EmployeeService employeeService;
    private final PosteService posteService;
    private final DemandeBesoinService demandeBesoinService;
    private final EtatDemandeService etatDemandeService;
    private final TypeDemandeService typeDemandeService;

    public HomeController(EmployeeService employeeService, PosteService posteService, DemandeBesoinService demandeBesoinService, EtatDemandeService etatDemandeService, TypeDemandeService typeDemandeService) {
        this.employeeService = employeeService;
        this.posteService = posteService;
        this.demandeBesoinService = demandeBesoinService;
        this.etatDemandeService = etatDemandeService;
        this.typeDemandeService = typeDemandeService;
    }

    @GetMapping("/home")
    @PareFeu
    public String home(@RequestParam(defaultValue = "dashboard") String section,
                       @RequestParam(required = false) String error,
                       @RequestParam(required = false) Integer demandeId,
                       Model model,
                       HttpSession session){
                Integer employeeId = (Integer) session.getAttribute("employeeId");
                Employee employee = employeeService.findById(employeeId);
                
                // partie nampiko 
                List<Employee> listeEmployee = employeeService.getEmployeesByDepartement(employee.getFiche().getDepartement().getId());
                
                //maka ny demande en attente
                List<DemandeBesoin> listeDemandes = demandeBesoinService.getDemandeEnAttente();

        String selectedSection = switch (section) {
            case "listePostes", "listeDemandes", "modifDemande", "voirDemande","creerPoste", "employees", "recruitment", "contracts", "onboarding" -> section;
            default -> "listePostes";
        };

        model.addAttribute("section", selectedSection);
        model.addAttribute("employeeConnecte", employee);
        model.addAttribute("error", error);

        //ito lay liste 
        model.addAttribute("listeEmployee", listeEmployee);
        model.addAttribute("listeDemandes", listeDemandes);
        if (demandeId != null && (selectedSection.equals("modifDemande") || selectedSection.equals("voirDemande"))) {
            model.addAttribute("demandeSelectionnee", demandeBesoinService.findById(demandeId));
        }
        return "pages/home";
    }

    @PostMapping("/demande")
    @PareFeu(roles = "Manager")
    public String demande(@RequestParam String title,
                         @RequestParam String department,
                         @RequestParam String contractType,
                         @RequestParam String reason,
                         @RequestParam BigDecimal budget,
                         @RequestParam String startDate,
                         @RequestParam int effectif
                         , HttpSession session) {
        try {
            posteService.findByLibelle(title);
            return "redirect:/home?section=creerPoste&error=Poste deja existant";
        } catch (EntityNotFoundException e) {
            
        }
        Employee manager = employeeService.findById((Integer) session.getAttribute("employeeId"));
        DemandeBesoin demandeBesoin = new DemandeBesoin(title, manager.getFiche().getDepartement(), manager, effectif, reason, budget, java.time.LocalDate.parse(startDate), etatDemandeService.findById(3), typeDemandeService.findById(1));
        demandeBesoinService.create(demandeBesoin);
        return "redirect:/home?section=creerPoste";
    }

    @PostMapping("/demande/{id}/modifier")
    @PareFeu(roles = "Manager")
    public String modifierDemande(@org.springframework.web.bind.annotation.PathVariable Integer id,
                                  @RequestParam String title,
                                  @RequestParam BigDecimal budget,
                                  @RequestParam String startDate,
                                  @RequestParam String reason,
                                  @RequestParam int effectif) {
        DemandeBesoin demande = demandeBesoinService.findById(id);
        demande.setIntitulePoste(title);
        demande.setEffectif(effectif);
        demande.setMotif(reason);
        demande.setBudget(budget);
        demande.setDateSouhaitee(java.time.LocalDate.parse(startDate));
        demandeBesoinService.update(id, demande);
        return "redirect:/home?section=voirDemande&demandeId=" + id;
    }

}

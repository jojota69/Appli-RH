package com.appliRh.RH.controller;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.model.Employee;
import com.appliRh.RH.annotation.PareFeu;
import com.appliRh.RH.service.EmployeeService;
import com.appliRh.RH.service.EmployeeFicheService;
import com.appliRh.RH.service.PosteService;
import com.appliRh.RH.model.DemandeBesoin;
import com.appliRh.RH.model.EmployeeFiche;
import com.appliRh.RH.service.DemandeBesoinService;
import com.appliRh.RH.service.EtatDemandeService;
import com.appliRh.RH.service.TypeDemandeService;
import com.appliRh.RH.service.TypeContratService;
import com.appliRh.RH.model.Poste;
import com.appliRh.RH.service.BudgetAlloueService;

import jakarta.servlet.http.HttpSession;
import jakarta.persistence.EntityNotFoundException;

@Controller
public class HomeController {

    private final EmployeeService employeeService;
    private final EmployeeFicheService employeeFicheService;
    private final PosteService posteService;
    private final DemandeBesoinService demandeBesoinService;
    private final EtatDemandeService etatDemandeService;
    private final TypeDemandeService typeDemandeService;
    private final TypeContratService typeContratService;
    private final BudgetAlloueService budgetAlloueService;

    public HomeController(EmployeeService employeeService, EmployeeFicheService employeeFicheService, PosteService posteService, DemandeBesoinService demandeBesoinService, EtatDemandeService etatDemandeService, TypeDemandeService typeDemandeService, TypeContratService typeContratService, BudgetAlloueService budgetAlloueService) {
        this.employeeService = employeeService;
        this.employeeFicheService = employeeFicheService;
        this.posteService = posteService;
        this.demandeBesoinService = demandeBesoinService;
        this.etatDemandeService = etatDemandeService;
        this.typeDemandeService = typeDemandeService;
        this.typeContratService = typeContratService;
        this.budgetAlloueService = budgetAlloueService;
    }

    @GetMapping("/home")
    @PareFeu
    public String home(@RequestParam(defaultValue = "dashboard") String section,
                       @RequestParam(required = false) String error,
                       @RequestParam(required = false) Integer demandeId,
                       @RequestParam(required = false) Integer selectedEmployeeId,
                       @RequestParam(required = false) Integer demandeBesoinId,
                       Model model,
                       HttpSession session){
                Integer employeeId = (Integer) session.getAttribute("employeeId");
                Employee employee = employeeService.findById(employeeId);
                
                // partie nampiko 
                List<Employee> listeEmployee = employeeService.getEmployeesByDepartement(employee.getFiche().getDepartement().getId());
                
                // Les managers voient les demandes de leur département.
                // Le Responsable RH voit toutes les demandes en cours de traitement.
                boolean responsableRh = "Responsable RH".equalsIgnoreCase(employee.getRole().getLibelle());
                boolean direction = "Direction".equalsIgnoreCase(employee.getRole().getLibelle());
                List<DemandeBesoin> demandesDuDepartement = responsableRh
                        ? demandeBesoinService.getToutesDemandesEnAttente()
                    : direction ? demandeBesoinService.getToutesDemandesEnCoursOuValideRh()
                        : demandeBesoinService.getDemandeEnAttente(employee.getFiche().getDepartement().getId());
        String selectedSection = switch (section) {
            case "listeEmployee", 
                "listePostesActuels",
                "voirPoste", 
                "remplacerPoste", 
                "listeDemandes", 
                "modifDemande", 
                "voirDemande",
                "voirDemandeRH",
                "listeDemandesDirection",
                "creerPoste", 
                "employees", 
                "recruitment", 
                "contracts", 
                "onboarding",
                "listeDemandesRH" -> section;
            default -> responsableRh ? "listeDemandesRH" : "listeEmployee";
        };

        List<DemandeBesoin> listePostesDemandes = demandeBesoinService.DepartementEtat(employee.getFiche().getDepartement().getId(), 1);
        List<Long> nombreParPoste = new ArrayList<>();
        for (DemandeBesoin demande : listePostesDemandes) {
            Poste poste = posteService.findByLibelle(demande.getIntitulePoste());
            long count = employeeFicheService.nombre(
                    employee.getFiche().getDepartement().getId(),
                    poste.getId());
            nombreParPoste.add(count);
        }

        model.addAttribute("listeTypeContrat", typeContratService.findAll());
        model.addAttribute("section", selectedSection);
        model.addAttribute("employeeConnecte", employee);
        model.addAttribute("error", error);
        model.addAttribute("nombreParPoste", nombreParPoste);
        model.addAttribute("listePostesDemandes", listePostesDemandes);
        //ito lay liste 
        model.addAttribute("listeEmployee", listeEmployee);
        model.addAttribute("listeDemandes", demandesDuDepartement);
        model.addAttribute("listeDemandesRH", responsableRh
                ? demandesDuDepartement
                : List.of());
        model.addAttribute("listeDemandesDirection", direction
            ? demandesDuDepartement
            : List.of());
        if (demandeId != null && (selectedSection.equals("modifDemande")
            || selectedSection.equals("voirDemande")
            || selectedSection.equals("voirDemandeRH"))) {
            model.addAttribute("demandeSelectionnee", demandeBesoinService.findById(demandeId));
        }
        if (demandeBesoinId != null && selectedSection.equals("voirPoste")) {
            model.addAttribute("demandeSelectionnee", demandeBesoinService.findById(demandeBesoinId));
            model.addAttribute("nombrePersonnesPoste",
                    employeeFicheService.nombre(
                            demandeBesoinService.findById(demandeBesoinId).getDepartement().getId(),
                            posteService.findByLibelle(demandeBesoinService.findById(demandeBesoinId).getIntitulePoste()).getId()));
        }
        if (selectedEmployeeId != null && selectedSection.equals("remplacerPoste")) {
            EmployeeFiche ficheSelectionnee = employeeService.findById(selectedEmployeeId).getFiche();
            model.addAttribute("ficheSelectionnee", ficheSelectionnee);
            Integer departementId = ficheSelectionnee.getDepartement().getId();
            Integer posteId = ficheSelectionnee.getPoste().getId();
            model.addAttribute("nombrePersonnesPoste",
                    employeeFicheService.nombre(departementId, posteId));
            model.addAttribute("budgetPoste",
                    demandeBesoinService.getBudgetPoste(departementId, ficheSelectionnee.getPoste().getLibelle()));
            model.addAttribute("budgetDepartement",
                    demandeBesoinService.getBudgetDepartement(departementId));
        }
        return "pages/home";
    }

    @PostMapping("/demande")
    @PareFeu(roles = "Manager")
    public String demande(@RequestParam List<String> title,
                         @RequestParam List<String> department,
                         @RequestParam List<String> contractType,
                         @RequestParam List<String> reason,
                         @RequestParam List<BigDecimal> budget,
                         @RequestParam List<String> startDate,
                         @RequestParam List<Integer> effectif,
                         @RequestParam(defaultValue = "false") boolean remplacement
                         , HttpSession session) {
        if (title.isEmpty()
                || title.size() != department.size()
                || title.size() != contractType.size()
                || title.size() != reason.size()
                || title.size() != budget.size()
                || title.size() != startDate.size()
                || title.size() != effectif.size()) {
            return "redirect:/home?section=creerPoste&error=Formulaire de poste incomplet";
        }

        if (!remplacement) {
            List<String> titresDejaUtilises = new ArrayList<>();
            for (String titre : title) {
                if (titresDejaUtilises.contains(titre) || posteExiste(titre)) {
                    return "redirect:/home?section=creerPoste&error=Poste deja existant : " + titre;
                }
                titresDejaUtilises.add(titre);
            }
        }

        Employee manager = employeeService.findById((Integer) session.getAttribute("employeeId"));
        int typeDemandeId = remplacement ? 2 : 1;
        List<DemandeBesoin> demandes = new ArrayList<>();
        for (int i = 0; i < title.size(); i++) {
            demandes.add(new DemandeBesoin(title.get(i), manager.getFiche().getDepartement(), manager,
                    effectif.get(i), reason.get(i), budget.get(i), java.time.LocalDate.parse(startDate.get(i)),
                    etatDemandeService.findById(3), typeDemandeService.findById(typeDemandeId),
                    typeContratService.findByLibelle(contractType.get(i))));
        }
        demandeBesoinService.createAll(demandes);
        return "redirect:/home?section=creerPoste";
    }

    private boolean posteExiste(String titre) {
        try {
            posteService.findByLibelle(titre);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    @PostMapping("/demande/{id}/modifier")
    @PareFeu(roles = "Manager")
    public String modifierDemande(@org.springframework.web.bind.annotation.PathVariable Integer id,
                                  @RequestParam String title,
                                  @RequestParam BigDecimal budget,
                                  @RequestParam String startDate,
                                  @RequestParam String reason,
                                  @RequestParam int effectif,
                                  @RequestParam int contractType) {
        DemandeBesoin demande = demandeBesoinService.findById(id);
        demande.setIntitulePoste(title);
        demande.setEffectif(effectif);
        demande.setMotif(reason);
        demande.setBudget(budget);
        demande.setDateSouhaitee(java.time.LocalDate.parse(startDate));
        demande.setTypeContrat(typeContratService.findById(contractType));
        demandeBesoinService.update(id, demande);
        return "redirect:/home?section=voirDemande&demandeId=" + id;
    }

    @PostMapping("/demande/{id}/valider")
    @PareFeu(roles = "Responsable RH")
    public String validerDemande(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        DemandeBesoin demande = demandeBesoinService.findById(id);
        BigDecimal budgetTotal = budgetAlloueService.getBudgetTotal();
        BigDecimal budgetEngage = demandeBesoinService.getBudgetEngageExcept(id);

        if (budgetEngage.add(demande.getBudget()).compareTo(budgetTotal) > 0) {
            demandeBesoinService.changerEtat(id, 2);
            return "redirect:/home?section=listeDemandesRH&error=Budget total de l'entreprise depasse";
        }

        demandeBesoinService.changerEtat(id, 4);
        return "redirect:/home?section=listeDemandesRH";
    }

    @PostMapping("/demande/{id}/rejeter")
    @PareFeu(roles = "Responsable RH")
    public String rejeterDemande(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        demandeBesoinService.changerEtat(id, 2);
        return "redirect:/home?section=listeDemandesRH";
    }

    @PostMapping("/demande/{id}/valider-direction")
    @PareFeu(roles = "Direction")
    public String validerDemandeDirection(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        demandeBesoinService.changerEtat(id, 1);
        return "redirect:/home?section=listeDemandesDirection";
    }

    @PostMapping("/demande/{id}/rejeter-direction")
    @PareFeu(roles = "Direction")
    public String rejeterDemandeDirection(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        demandeBesoinService.changerEtat(id, 2);
        return "redirect:/home?section=listeDemandesDirection";
    }

    @GetMapping ("/poste/{id}/supprimer")
    @PareFeu(roles = "Manager")
    public String supprimerDemande(@org.springframework.web.bind.annotation.PathVariable Integer id, HttpSession session) {
        Employee manager = employeeService.findById((Integer) session.getAttribute("employeeId"));
        DemandeBesoin demande = demandeBesoinService.findById(id);
        DemandeBesoin demandeBesoin = new DemandeBesoin(demande.getIntitulePoste(), manager.getFiche().getDepartement(), manager, demande.getEffectif(), demande.getMotif(), demande.getBudget(), java.time.LocalDate.now(), etatDemandeService.findById(3), typeDemandeService.findById(3), typeContratService.findByLibelle(demande.getTypeContrat().getLibelle()));
        demandeBesoinService.create(demandeBesoin);
        return "redirect:/home?section=listePostesActuels";
    }
}

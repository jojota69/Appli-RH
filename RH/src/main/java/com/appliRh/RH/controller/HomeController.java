package com.appliRh.RH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.model.Employee;
import com.appliRh.RH.annotation.PareFeu;
import com.appliRh.RH.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final EmployeeService employeeService;

    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    @PareFeu
    public String home(@RequestParam(defaultValue = "dashboard") String section,
                       Model model,
                       HttpSession session){
                Integer employeeId = (Integer) session.getAttribute("employeeId");
                Employee employee = employeeService.findById(employeeId);
        String selectedSection = switch (section) {
            case "listePostes", "creerPoste", "employees", "recruitment", "contracts", "onboarding" -> section;
            default -> "dashboard";
        };

        model.addAttribute("section", selectedSection);
        model.addAttribute("employeeConnecte", employee);
        return "pages/home";
    }

}

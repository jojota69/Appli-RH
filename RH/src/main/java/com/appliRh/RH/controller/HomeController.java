package com.appliRh.RH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.model.Employee;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "dashboard") String section,
                       Model model,
                       HttpSession session){
        Employee employee = (Employee) session.getAttribute("employeeConnecte");
        if (employee == null) {
            return "redirect:/";
        }

        String selectedSection = switch (section) {
            case "employees", "recruitment", "contracts", "onboarding" -> section;
            default -> "dashboard";
        };

        model.addAttribute("section", selectedSection);
        model.addAttribute("employeeConnecte", employee);
        return "pages/home";
    }

}

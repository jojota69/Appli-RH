package com.appliRh.RH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.model.Employee;

import jakarta.servlet.http.HttpSession;

@Controller 
public class EmbaucheController {
    
    public EmbaucheController() {

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
    

}


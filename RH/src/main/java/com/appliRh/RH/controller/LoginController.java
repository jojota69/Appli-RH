package com.appliRh.RH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.model.Employee;
import com.appliRh.RH.service.EmployeeService;
import com.appliRh.RH.annotation.PareFeu;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final EmployeeService employeeService;

    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String index() {
        return "pages/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String motDePasse,
                        HttpServletRequest request) {
        Employee employee;
        try {
            employee = employeeService.findByEmail(email);
        } catch (Exception exception) {
            return "redirect:/";
        }

        if (!employee.getMotDePasse().equals(motDePasse)) {
            return "redirect:/";
        }

        request.getSession().setAttribute("employeeId", employee.getId());
        return "redirect:/home"; 
    }

    @GetMapping("/logout")
    @PareFeu
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
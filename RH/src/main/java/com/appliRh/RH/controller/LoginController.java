package com.appliRh.RH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appliRh.RH.modele.LoginForm;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form){
        String username = form.getUsername();
        String pwd = form.getPwd();

        
    }

}
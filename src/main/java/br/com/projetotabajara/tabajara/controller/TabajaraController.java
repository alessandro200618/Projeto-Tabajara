package br.com.projetotabajara.tabajara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TabajaraController {
    
    @GetMapping({"/", "/tabajara"})
    public String index(Model model){
        return "index";
    }
}

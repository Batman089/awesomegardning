package com.company.project.controllers;

import com.company.project.entity.Plant;
import com.company.project.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PlantRepository repository;

    @GetMapping("/")
    public String showHome(String name, Model model) {
        Plant dockerPlant = repository.findById(1).orElse(new Plant("Not Found 😕"));
        model = model.addAttribute("name", dockerPlant.getName());
        return "home";
    }

}

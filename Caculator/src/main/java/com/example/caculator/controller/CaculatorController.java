package com.example.caculator.controller;

import com.example.caculator.entity.Caculator;
import com.example.caculator.service.ICaculartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CaculatorController {

    private final ICaculartService service;

    @Autowired
    public CaculatorController(ICaculartService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("form", new Caculator());
        return "Index"; // views/Index.jsp
    }

    @PostMapping("/calc")
    public String calc(@ModelAttribute("form") Caculator form, Model model) {
        model.addAttribute("form", service.calculate(form));
        return "Result"; // views/Result.jsp
    }
}

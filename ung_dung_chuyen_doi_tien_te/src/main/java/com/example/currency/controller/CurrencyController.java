package com.example.currency.controller;

import com.example.currency.model.Conversion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CurrencyController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("conversion", new Conversion());
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@ModelAttribute("conversion") Conversion form, Model model) {
        form.setVnd(form.getRate() * form.getUsd());
        model.addAttribute("conversion", form);
        return "result";
    }
}

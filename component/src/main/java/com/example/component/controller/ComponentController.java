package com.example.component.controller;

import com.example.component.entity.Component;
import com.example.component.service.IComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComponentController {

    private final IComponentService service;

    @Autowired
    public ComponentController(IComponentService service) {
        this.service = service;
    }

    @ModelAttribute
    public void addCommons(Model model) {
        model.addAttribute("languages", service.languages());
        model.addAttribute("pageSizes", service.pageSizes());
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("component", service.getCurrent()); // object để binding
        return "index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("component") Component form, Model model) {
        service.update(form);
        model.addAttribute("component", form);
        return "result";
    }

    @PostMapping("/cancel")
    public String cancel() {
        return "redirect:/";
    }
}

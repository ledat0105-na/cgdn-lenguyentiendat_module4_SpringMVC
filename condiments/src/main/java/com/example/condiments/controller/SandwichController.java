package com.example.condiments.controller;

import com.example.condiments.entity.CondimentSelection;
import com.example.condiments.service.ICondimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {

    private final ICondimentService service;

    @Autowired
    public SandwichController(ICondimentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("allCondiments", service.getAll());
        return "index"; // /WEB-INF/views/index.jsp
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiment,
                       Model model) {
        CondimentSelection selection = service.buildSelection(condiment);
        model.addAttribute("selection", selection);
        return "result"; // /WEB-INF/views/result.jsp
    }
}

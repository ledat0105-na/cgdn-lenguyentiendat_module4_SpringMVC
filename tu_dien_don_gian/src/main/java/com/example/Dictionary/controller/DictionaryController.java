package com.example.Dictionary.controller;

import com.example.Dictionary.model.Word;
import com.example.Dictionary.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    private final IDictionaryService service;

    @Autowired
    public DictionaryController(IDictionaryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/translate")
    public String translate(@RequestParam("word") String word, Model model) {
        Word w = service.translate(word);
        model.addAttribute("word", word);
        model.addAttribute("meaning", w != null ? w.getVietnamese() : null);
        model.addAttribute("found", w != null);
        return "result";
    }
}

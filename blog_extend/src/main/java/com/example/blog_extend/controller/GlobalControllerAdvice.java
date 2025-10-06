package com.example.blog_extend.controller;

import lombok.RequiredArgsConstructor;
import com.example.blog_extend.repository.CategoryRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final CategoryRepository categoryRepo;

    @ModelAttribute("menuCategories")
    public Object menuCategories() {
        return categoryRepo.findAll();
    }
}


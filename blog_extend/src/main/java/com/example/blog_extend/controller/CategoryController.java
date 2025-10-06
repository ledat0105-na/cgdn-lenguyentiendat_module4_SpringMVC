package com.example.blog_extend.controller;

import lombok.RequiredArgsConstructor;
import com.example.blog_extend.entity.Category;
import com.example.blog_extend.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "category/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid Category category,
                         BindingResult br,
                         Model model) {
        if (categoryRepo.existsByNameIgnoreCase(category.getName())) {
            br.rejectValue("name", "exists", "Tên danh mục đã tồn tại");
        }
        if (br.hasErrors()) return "category/form";
        categoryRepo.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryRepo.findById(id).orElseThrow());
        return "category/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @ModelAttribute @Valid Category category,
                       BindingResult br) {
        category.setId(id);
        if (br.hasErrors()) return "category/form";
        categoryRepo.save(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        categoryRepo.deleteById(id);
        return "redirect:/admin/categories";
    }
}
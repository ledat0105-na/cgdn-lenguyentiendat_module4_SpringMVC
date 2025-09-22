package com.example.product_management.controller;

import com.example.product_management.entity.Product;
import com.example.product_management.service.IProductSerice; // giữ đúng tên interface của bạn
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductSerice service;

    @Autowired
    public ProductController(IProductSerice service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        model.addAttribute("products", (q == null ? service.findAll() : service.searchByName(q)));
        model.addAttribute("q", q);
        return "list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("action", "create");
        return "form";
    }

    @PostMapping
    public String create(@ModelAttribute("product") Product form) {
        service.create(form);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Product p = service.findById(id).orElse(null);
        if (p == null) return "redirect:/products";
        model.addAttribute("product", p);
        model.addAttribute("action", "edit");
        return "form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("product") Product form) {
        service.update(id, form);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Product p = service.findById(id).orElse(null);
        if (p == null) return "redirect:/products";
        model.addAttribute("product", p);
        return "view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/products";
    }
}

package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.service.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService service;

    public BlogController(IBlogService service) { this.service = service; }

    @GetMapping({"", "/"})
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        model.addAttribute("blogs", service.search(q));
        model.addAttribute("q", q);
        return "list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("action", "create");
        return "form";
    }

    @PostMapping
    public String create(@ModelAttribute("blog") Blog form) {
        service.create(form);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Blog b = service.findById(id).orElse(null);
        if (b == null) return "redirect:/blogs";
        model.addAttribute("blog", b);
        return "view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Blog b = service.findById(id).orElse(null);
        if (b == null) return "redirect:/blogs";
        model.addAttribute("blog", b);
        model.addAttribute("action", "edit");
        return "form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("blog") Blog form) {
        service.update(id, form);
        return "redirect:/blogs";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/blogs";
    }
}

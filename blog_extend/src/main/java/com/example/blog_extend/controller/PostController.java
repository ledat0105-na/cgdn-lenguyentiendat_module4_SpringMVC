package com.example.blog_extend.controller;

import lombok.RequiredArgsConstructor;
import com.example.blog_extend.entity.Post;
import com.example.blog_extend.repository.CategoryRepository;
import com.example.blog_extend.repository.PostRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepo;
    private final CategoryRepository categoryRepo;

    @GetMapping({"/", "/posts"})
    public String listPosts(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size,
                            @RequestParam(value = "q", required = false) String q,
                            @RequestParam(value = "cat", required = false) Long catId,
                            @RequestParam(value = "dir", defaultValue = "desc") String dir) {

        Sort sort = Sort.by("createdAt");
        sort = "asc".equalsIgnoreCase(dir) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> postPage;
        if (q != null && !q.isBlank()) {
            postPage = postRepo.findByTitleContainingIgnoreCase(q.trim(), pageable);
        } else if (catId != null) {
            postPage = postRepo.findByCategory_Id(catId, pageable);
        } else {
            postPage = postRepo.findAll(pageable);
        }

        model.addAttribute("postPage", postPage);
        model.addAttribute("q", q);
        model.addAttribute("cat", catId);
        model.addAttribute("dir", dir);
        return "post/list";
    }

    @GetMapping("/admin/posts/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryRepo.findAll());
        return "post/form";
    }

    @PostMapping("/admin/posts/create")
    public String create(@ModelAttribute @Valid Post post, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categories", categoryRepo.findAll());
            return "post/form";
        }
        // Ensure category reference is managed (avoid transient/detached association issues)
        if (post.getCategory() != null && post.getCategory().getId() != null) {
            post.setCategory(categoryRepo.findById(post.getCategory().getId()).orElseThrow());
        }
        postRepo.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/admin/posts/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).orElseThrow());
        model.addAttribute("categories", categoryRepo.findAll());
        return "post/form";
    }

    @PostMapping("/admin/posts/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute @Valid Post post, BindingResult br, Model model) {
        post.setId(id);
        if (br.hasErrors()) {
            model.addAttribute("categories", categoryRepo.findAll());
            return "post/form";
        }
        // Ensure category reference is managed (avoid transient/detached association issues)
        if (post.getCategory() != null && post.getCategory().getId() != null) {
            post.setCategory(categoryRepo.findById(post.getCategory().getId()).orElseThrow());
        }
        postRepo.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/admin/posts/{id}/delete")
    public String delete(@PathVariable Long id) {
        postRepo.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postRepo.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "post/detail";
    }
}
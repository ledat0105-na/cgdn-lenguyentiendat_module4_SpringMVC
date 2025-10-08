package com.example.form_register.controller;

import com.example.form_register.model.User;
import com.example.form_register.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class FormController {

    private final IUserService userService;

    @GetMapping({"/", "/register"})
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/register")
    public String submit(@Valid @ModelAttribute("user") User user,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        try {
            User saved = userService.register(user);
            model.addAttribute("user", saved);
            return "result";
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage() == null ? "" : ex.getMessage().toLowerCase();
            if (msg.contains("email")) {
                result.rejectValue("email", "duplicate", ex.getMessage());
            } else if (msg.contains("phone")) {
                result.rejectValue("phonenumber", "duplicate", ex.getMessage());
            } else {
                result.reject("global", ex.getMessage());
            }
            return "index";
        }
    }
}

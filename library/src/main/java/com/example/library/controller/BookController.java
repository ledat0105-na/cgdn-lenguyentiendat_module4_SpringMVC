package com.example.library.controller;

import com.example.library.model.BorrowTicket;
import com.example.library.service.ILibraryService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final ILibraryService service;

    @GetMapping({"/", "/books"})
    public String list(Model model) {
        model.addAttribute("books", service.listBooks());
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("book", service.getBook(id));
        return "book-detail";
    }

    @PostMapping("/books/{id}/borrow")
    public String borrow(@PathVariable Long id, Model model) {
        BorrowTicket ticket = service.borrow(id);
        model.addAttribute("book", service.getBook(id));
        model.addAttribute("ticket", ticket);
        return "book-detail";
    }

    @GetMapping("/return")
    public String returnForm() { return "return"; }

    @PostMapping("/return")
    public String doReturn(@RequestParam @NotBlank String code, Model model) {
        var book = service.returnByCode(code.trim());
        model.addAttribute("message", "Đã trả sách thành công!");
        model.addAttribute("book", book);
        return "return";
    }
}

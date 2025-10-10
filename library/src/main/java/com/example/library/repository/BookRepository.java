package com.example.library.repository;

import com.example.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final IBookRepository jpa;

    public List<Book> findAll() { return jpa.findAll(); }
    public Book findByIdOrThrow(Long id) {
        return jpa.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }
    public Book save(Book b) { return jpa.save(b); }
}

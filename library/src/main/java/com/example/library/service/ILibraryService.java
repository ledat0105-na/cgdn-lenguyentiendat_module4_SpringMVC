package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BorrowTicket;

import java.util.List;

public interface ILibraryService {
    List<Book> listBooks();
    Book getBook(Long id);
    BorrowTicket borrow(Long bookId);
    Book returnByCode(String code);
}

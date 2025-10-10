package com.example.library.service;

import com.example.library.exception.InvalidBorrowCodeException;
import com.example.library.exception.OutOfStockException;
import com.example.library.model.Book;
import com.example.library.model.BorrowTicket;
import com.example.library.repository.IBookRepository;
import com.example.library.repository.IBorrowTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryService implements ILibraryService {

    private final IBookRepository bookRepo;
    private final IBorrowTicketRepository ticketRepo;
    private final SecureRandom random = new SecureRandom();

    @Override @Transactional(readOnly = true)
    public List<Book> listBooks() { return bookRepo.findAll(); }

    @Override @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    @Override
    public BorrowTicket borrow(Long bookId) {
        Book book = getBook(bookId);
        if (book.getQuantity() <= 0) throw new OutOfStockException("Sách đã hết. Không thể mượn.");
        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);

        String code = generateUniqueCode();
        BorrowTicket ticket = BorrowTicket.builder()
                .code(code).book(book).returned(false).build();
        return ticketRepo.save(ticket);
    }

    @Override
    public Book returnByCode(String code) {
        BorrowTicket ticket = ticketRepo.findByCodeAndReturnedFalse(code)
                .orElseThrow(() -> new InvalidBorrowCodeException("Mã mượn không hợp lệ hoặc đã trả."));
        Book book = ticket.getBook();
        book.setQuantity(book.getQuantity() + 1);
        ticket.setReturned(true);
        ticketRepo.save(ticket);
        return bookRepo.save(book);
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = String.format("%05d", random.nextInt(100000));
        } while (ticketRepo.existsByCode(code));
        return code;
    }
}

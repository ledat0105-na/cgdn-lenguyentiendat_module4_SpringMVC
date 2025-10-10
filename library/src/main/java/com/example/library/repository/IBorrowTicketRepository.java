package com.example.library.repository;

import com.example.library.model.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBorrowTicketRepository extends JpaRepository<BorrowTicket, Long> {
    Optional<BorrowTicket> findByCodeAndReturnedFalse(String code);
    boolean existsByCode(String code);
}

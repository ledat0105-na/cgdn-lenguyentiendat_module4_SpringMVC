package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "borrow_tickets")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class BorrowTicket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5, nullable = false, unique = true)
    private String code;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    @Column(nullable = false)
    private boolean returned;
}

package com.example.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "books")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable=false, unique = true)
    private String title;

    @Min(0) @Column(nullable=false)
    private int quantity;
}

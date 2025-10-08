package com.example.form_register.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname is required")
    @Size(min = 5, max = 45, message = "Firstname must be 5-45 characters")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Size(min = 5, max = 45, message = "Lastname must be 5-45 characters")
    private String lastname;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(0\\d{9,10})$", message = "Phone must start with 0 and be 10-11 digits")
    private String phonenumber;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be >= 18")
    private Integer age;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}

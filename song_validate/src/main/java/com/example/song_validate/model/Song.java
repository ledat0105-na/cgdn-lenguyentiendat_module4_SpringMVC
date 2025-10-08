package com.example.song_validate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không vượt quá 800 ký tự")
    @Pattern(
            regexp = "^[\\p{L}\\p{M}0-9 ]+$",
            message = "Tên bài hát không được chứa ký tự đặc biệt"
    )
    private String name;

    @NotBlank(message = "Tên nghệ sĩ không được để trống")
    @Size(max = 300, message = "Tên nghệ sĩ không vượt quá 300 ký tự")
    @Pattern(
            regexp = "^[\\p{L}\\p{M}0-9 ]+$",
            message = "Tên nghệ sĩ không được chứa ký tự đặc biệt"
    )
    private String artist;

    @NotBlank(message = "Thể loại nhạc không được để trống")
    @Size(max = 1000, message = "Thể loại không vượt quá 1000 ký tự")
    @Pattern(
            regexp = "^[\\p{L}\\p{M}0-9 ,]+$",
            message = "Thể loại chỉ được chứa chữ, số, khoảng trắng và dấu phẩy"
    )
    private String genre;
}

package com.example.song_validate.controller;

import com.example.song_validate.dto.SongDto;
import com.example.song_validate.model.Song;
import com.example.song_validate.service.ISongService;
import com.example.song_validate.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final ISongService service;
    private final SongService songService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("songs", service.findAll());
        return "song-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("song", new SongDto());
        model.addAttribute("mode", "create");
        return "song-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("song") SongDto songDto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "create");
            return "song-form";
        }
        Song song = songService.convertToEntity(songDto);
        service.create(song);
        return "redirect:/songs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Song song = service.findById(id);
        if (song == null) return "redirect:/songs";
        SongDto songDto = songService.convertToDto(song);
        model.addAttribute("song", songDto);
        model.addAttribute("mode", "edit");
        return "song-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("song") SongDto songDto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "song-form";
        }
        Song song = songService.convertToEntity(songDto);
        service.update(id, song);
        return "redirect:/songs";
    }

    // Xóa
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/songs";
    }
}

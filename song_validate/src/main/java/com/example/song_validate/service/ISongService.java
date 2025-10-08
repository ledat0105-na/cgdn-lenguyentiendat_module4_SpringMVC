package com.example.song_validate.service;

import com.example.song_validate.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    Song create(Song song);
    Song update(Long id, Song song);
    void delete(Long id);
}

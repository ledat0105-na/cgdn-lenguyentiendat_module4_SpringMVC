package com.example.song_validate.repository;

import com.example.song_validate.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SongRepository {

    private final ISongRepository repo;

    public List<Song> findAll() {
        return repo.findAll();
    }

    public Song findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Song save(Song song) {
        return repo.save(song);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

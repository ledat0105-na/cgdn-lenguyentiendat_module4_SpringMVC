package com.example.song_validate.service;

import com.example.song_validate.dto.SongDto;
import com.example.song_validate.model.Song;
import com.example.song_validate.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SongService implements ISongService {

    private final SongRepository repo;

    @Override
    @Transactional(readOnly = true)
    public List<Song> findAll() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Song findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Song create(Song song) {
        return repo.save(song);
    }

    @Override
    public Song update(Long id, Song song) {
        Song exist = repo.findById(id);
        if (exist == null) throw new IllegalArgumentException("Không tìm thấy bài hát");
        exist.setName(song.getName());
        exist.setArtist(song.getArtist());
        exist.setGenre(song.getGenre());
        return repo.save(exist);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

    // Conversion methods between DTO and Entity
    public SongDto convertToDto(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .artist(song.getArtist())
                .genre(song.getGenre())
                .build();
    }

    public Song convertToEntity(SongDto songDto) {
        return Song.builder()
                .id(songDto.getId())
                .name(songDto.getName())
                .artist(songDto.getArtist())
                .genre(songDto.getGenre())
                .build();
    }
}

package com.music.api.music.services;

import com.music.api.genres.entity.Genre;
import com.music.api.genres.errors.GenreNotFoundedError;
import com.music.api.genres.services.GenreService;
import com.music.api.music.entity.Music;
import com.music.api.music.errors.MusicNotFoundedError;
import com.music.api.music.repository.MusicRepository;
import com.music.api.singer.entity.Singer;
import com.music.api.singer.errors.SingerNotFoundedError;
import com.music.api.singer.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private SingerService singerService;

    public Music create(String name, Long genreId, Long singerId) throws GenreNotFoundedError, SingerNotFoundedError {

        Genre genre = this.genreService.getById(genreId);
        Singer singer = this.singerService.getById(singerId);

        Music music = new Music();
        music.setName(name);
        music.setGenre(genre);
        music.setSinger(singer);

        return this.musicRepository.save(music);
    }

    public List<Music> getAll() {
        return this.musicRepository.findAll();
    }

    public void delete(long parseLong) {
        Optional<Music> optionalMusic = this.musicRepository.findById(parseLong);

        if(optionalMusic.isPresent()) {
            this.musicRepository.delete(optionalMusic.get());
        }
    }

    public Music getById(long id) throws MusicNotFoundedError {
        Optional<Music> optionalMusic = this.musicRepository.findById(id);

        if(optionalMusic.isPresent()) {
            return optionalMusic.get();
        }

        throw new MusicNotFoundedError("Music not founded");
    }

    public List<Music> getByGenres(Set<Genre> genres) {
        List<Music> allMusics = this.musicRepository.findAll();

        return allMusics.stream()
                .filter(music -> genres.stream().anyMatch(genre -> genre.getId() == music.getGenre().getId()))
                .collect(Collectors.toList());
    }
}

package com.music.api.genres.services;

import com.music.api.genres.entity.Genre;
import com.music.api.genres.errors.GenreNotFoundedError;
import com.music.api.genres.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre create(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return this.genreRepository.save(genre);
    }

    public List<Genre> getAll() {
        return this.genreRepository.findAll();
    }

    public void delete(long id) {
        Optional<Genre> genre = this.genreRepository.findById(id);

        if(genre.isPresent()) {
            this.genreRepository.delete(genre.get());
        }
    }

    public Genre getById(Long id) throws GenreNotFoundedError {
        Optional<Genre> optionalGenre = this.genreRepository.findById(id);

        if(!optionalGenre.isPresent()) {
            throw new GenreNotFoundedError();
        }

        return optionalGenre.get();
    }
}

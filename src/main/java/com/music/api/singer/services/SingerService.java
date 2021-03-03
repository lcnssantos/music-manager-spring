package com.music.api.singer.services;

import com.music.api.genres.entity.Genre;
import com.music.api.genres.errors.GenreNotFoundedError;
import com.music.api.genres.services.GenreService;
import com.music.api.singer.entity.Singer;
import com.music.api.singer.errors.SingerNotFoundedError;
import com.music.api.singer.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService {
    @Autowired
    private SingerRepository singerRepository;
    @Autowired
    private GenreService genreService;

    public Singer create(String name) {
        Singer singer = new Singer();
        singer.setName(name);
        return this.singerRepository.save(singer);
    }

    public void delete(Long id) {
        Optional<Singer> singer = this.singerRepository.findById(id);

        if(singer.isPresent()) {
            this.singerRepository.delete(singer.get());
        }
    }

    public List<Singer> getAllSingers() {
        return this.singerRepository.findAll();
    }

    public void setGenre(Long singerId, Long genreId) throws GenreNotFoundedError, SingerNotFoundedError {
        Optional<Singer> optionalSinger = this.singerRepository.findById(singerId);
        Genre genre = this.genreService.getById(genreId);

        if(optionalSinger.isPresent()) {
            Singer singer = optionalSinger.get();
            singer.addGenre(genre);
            this.singerRepository.save(singer);
            return;
        }

        throw new SingerNotFoundedError("Singer not founded");
    }

    public void removeGenre(Long singerId, Long genreId) throws GenreNotFoundedError, SingerNotFoundedError {
        Optional<Singer> optionalSinger = this.singerRepository.findById(singerId);
        Genre genre = this.genreService.getById(genreId);

        if(optionalSinger.isPresent()) {
            Singer singer = optionalSinger.get();
            singer.removeGenre(genre);
            this.singerRepository.save(singer);
            return;
        }

        throw new SingerNotFoundedError("Singer not founded");
    }

    public Singer getById(Long singerId) throws SingerNotFoundedError {
        Optional<Singer> optionalSinger = this.singerRepository.findById(singerId);

        if(optionalSinger.isPresent()) {
            return optionalSinger.get();
        }

        throw new SingerNotFoundedError("Singer not founded");
    }
}

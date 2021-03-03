package com.music.api.genres.controllers;

import com.music.api.genres.entity.Genre;
import com.music.api.genres.requests.CreateGenreRequest;
import com.music.api.genres.responses.GenreResponse;
import com.music.api.genres.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreResponse> createUser(@Valid @RequestBody CreateGenreRequest createRequest) {
        try {
            Genre genre = this.genreService.create(createRequest.name);
            GenreResponse response = new GenreResponse(genre, false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenreResponse>> getAllGenres() {
        try {
            List<Genre> genres = this.genreService.getAll();

            List<GenreResponse> response = genres.stream()
                    .map(genre -> new GenreResponse(genre, true))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteGenre(@PathVariable("id") String id) {
        try {
            this.genreService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

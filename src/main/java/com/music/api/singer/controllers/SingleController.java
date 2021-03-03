package com.music.api.singer.controllers;

import com.music.api.singer.entity.Singer;
import com.music.api.singer.requests.CreateSingerRequest;
import com.music.api.singer.responses.SingerResponse;
import com.music.api.singer.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("singer")
public class SingleController {
    @Autowired
    private SingerService singerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingerResponse> createSinger(@Valid @RequestBody CreateSingerRequest createRequest) {
        try {
            Singer singer = this.singerService.create(createRequest.name);
            SingerResponse response = new SingerResponse(singer, false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSinger(@Valid @PathVariable(value = "id") String id) {
        try {
            this.singerService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SingerResponse>> getSingers() {
        try {
            List<Singer> singers = this.singerService.getAllSingers();

            List<SingerResponse> response = singers.stream()
                    .map(singer -> new SingerResponse(singer, true))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "{singerId}/{genreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setGenreToSinger(
            @PathVariable("singerId") String singerId,
            @PathVariable("genreId") String genreId
    ) {
        try {
            this.singerService.setGenre(Long.parseLong(singerId), Long.parseLong(genreId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "{singerId}/{genreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeGenreFromSinger(
            @PathVariable("singerId") String singerId,
            @PathVariable("genreId") String genreId
    ) {
        try {
            this.singerService.removeGenre(Long.parseLong(singerId), Long.parseLong(genreId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

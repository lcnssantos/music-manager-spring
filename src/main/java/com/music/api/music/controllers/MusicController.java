package com.music.api.music.controllers;

import com.music.api.music.entity.Music;
import com.music.api.music.requests.CreateMusicRequest;
import com.music.api.music.responses.MusicResponse;
import com.music.api.music.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MusicResponse> createMusic(@Valid @RequestBody CreateMusicRequest request) {
        try {
            Music music = this.musicService.create(request.name, request.genreId, request.singerId);
            MusicResponse response = new MusicResponse(music);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteMusic(@PathVariable("id") String id) {
        try {
            this.musicService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MusicResponse>> getAll() {
        try {
            List<Music> musics = this.musicService.getAll();
            List<MusicResponse> response = musics.stream().map(music -> new MusicResponse(music)).collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

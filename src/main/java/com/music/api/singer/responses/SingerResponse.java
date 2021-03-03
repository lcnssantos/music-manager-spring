package com.music.api.singer.responses;

import com.music.api.genres.responses.GenreResponse;
import com.music.api.music.responses.MusicResponse;
import com.music.api.singer.entity.Singer;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class SingerResponse {
    public Long id;
    public String name;
    public Date date;
    public Set<GenreResponse> genres;
    public Set<MusicResponse> musics;

    public SingerResponse(Singer singer, Boolean lazy) {
        this.name = singer.getName();
        this.date = singer.getDate();
        this.id = singer.getId();

        if(lazy) {
            this.genres = singer.getGenres().stream()
                    .map(genre -> new GenreResponse(genre, false))
                    .collect(Collectors.toSet());

            this.musics = singer.getMusics().stream()
                    .map(music -> new MusicResponse(music))
                    .collect(Collectors.toSet());
        }
    }
}

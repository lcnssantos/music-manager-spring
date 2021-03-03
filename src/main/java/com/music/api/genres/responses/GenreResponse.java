package com.music.api.genres.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.music.api.genres.entity.Genre;
import com.music.api.music.responses.MusicResponse;
import com.music.api.singer.entity.Singer;
import com.music.api.singer.responses.SingerResponse;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class GenreResponse {
    public Long id;
    public String name;
    public Date date;
    public Set<SingerResponse> singers;
    public Set<MusicResponse> musics;

    public GenreResponse(Genre genre, Boolean lazy) {
        this.id = genre.getId();
        this.name = genre.getName();
        this.date = genre.getDate();

        if(lazy) {
            this.singers = genre.getSingers().stream()
                    .map(singer -> new SingerResponse(singer, false))
                    .collect(Collectors.toSet());

            this.musics = genre.getMusics().stream()
                    .map(music -> new MusicResponse(music))
                    .collect(Collectors.toSet());
        }
    }
}

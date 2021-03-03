package com.music.api.music.responses;

import com.music.api.genres.responses.GenreResponse;
import com.music.api.music.entity.Music;
import com.music.api.singer.responses.SingerResponse;

import java.util.Date;

public class MusicResponse {

    public Long id;
    public String name;
    public Date date;
    public GenreResponse genre;
    public SingerResponse singer;

    public MusicResponse(Music music) {
        this.id = music.getId();
        this.name = music.getName();
        this.date = music.getDate();
        this.genre = new GenreResponse(music.getGenre(), false);
        this.singer = new SingerResponse(music.getSinger(), false);
    }
}

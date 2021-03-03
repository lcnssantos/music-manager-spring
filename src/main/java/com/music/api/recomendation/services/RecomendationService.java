package com.music.api.recomendation.services;

import com.music.api.avaliation.services.AvaliationService;
import com.music.api.music.entity.Music;
import com.music.api.music.services.MusicService;
import com.music.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendationService {
    @Autowired
    private MusicService musicService;
    @Autowired
    private AvaliationService avaliationService;


    public List<Music> get(User user)  {
        List<Music> musics = this.musicService.getByGenres(user.getGenres());

        return musics.stream()
                .filter(music -> !user.hasGivenScore(music))
                .collect(Collectors.toList());

    }
}

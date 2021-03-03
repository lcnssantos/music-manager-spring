package com.music.api.recomendation.responses;


import com.music.api.avaliation.entity.Avaliation;
import com.music.api.music.entity.Music;
import com.music.api.music.responses.MusicResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecomendationResponse {
    public MusicResponse music;
    public Double average;


    public RecomendationResponse(Music music) {
        this.music = new MusicResponse(music);
        Set<Avaliation> avaliations = music.getAvaliations();
        List<Double> scores = avaliations.stream()
                .map(avaliation -> avaliation.getScore().doubleValue())
                .collect(Collectors.toList());

        this.average = scores.stream()
                .reduce(0D, (average, element) -> average + (element / avaliations.size()));
    }
}

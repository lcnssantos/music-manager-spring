package com.music.api.avaliation.services;

import com.music.api.avaliation.entity.Avaliation;
import com.music.api.avaliation.repository.AvaliationRepository;
import com.music.api.music.entity.Music;
import com.music.api.music.errors.MusicNotFoundedError;
import com.music.api.music.services.MusicService;
import com.music.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliationService {
    @Autowired
    private MusicService musicService;
    @Autowired
    private AvaliationRepository avaliationRepository;

    public void create(User user, Short score, Long musicId) throws MusicNotFoundedError {
        Music music = this.musicService.getById(musicId);
        Avaliation avaliation = new Avaliation();

        avaliation.setUser(user);
        avaliation.setMusic(music);
        avaliation.setScore(score);

        this.avaliationRepository.save(avaliation);
    }
}

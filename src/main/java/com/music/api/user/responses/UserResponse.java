package com.music.api.user.responses;

import com.music.api.genres.responses.GenreResponse;
import com.music.api.user.entity.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    public Long id;
    public String name;
    public String email;
    public Date date;
    public List<GenreResponse> genres;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.date = user.getDate();

        if(user.getGenres() != null) {
            this.genres = user.getGenres().stream()
                    .map(genre -> new GenreResponse(genre, false))
                    .collect(Collectors.toList());
        }
    }
}

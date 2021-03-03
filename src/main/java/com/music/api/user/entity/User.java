package com.music.api.user.entity;

import com.music.api.avaliation.entity.Avaliation;
import com.music.api.genres.entity.Genre;
import com.music.api.music.entity.Music;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false, unique = true)
    @Setter
    private String email;

    @Column(nullable = false)
    private Date date;

    @OneToMany(mappedBy = "user")
    private Set<Avaliation> avaliations;

    @ManyToMany
    @JoinTable(
            name = "users_genres",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @PrePersist
    private void onCreate() {
        this.date = new Date();
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
    }

    public boolean hasGivenScore(Music music) {
        return this.avaliations.stream().anyMatch(avaliation -> avaliation.getMusic().getId() == music.getId());
    }
}

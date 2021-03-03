package com.music.api.singer.entity;

import com.music.api.genres.entity.Genre;

import com.music.api.music.entity.Music;
import com.music.api.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
public class Singer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    private String name;

    @Column(nullable = false)
    private Date date;

    @OneToMany(mappedBy = "singer")
    private Set<Music> musics;

    @ManyToMany
    @JoinTable(
            name = "singers_genres",
            joinColumns = @JoinColumn(name = "singer_id"),
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
}

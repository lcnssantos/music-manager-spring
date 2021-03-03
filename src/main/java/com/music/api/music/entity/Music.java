package com.music.api.music.entity;

import com.music.api.avaliation.entity.Avaliation;
import com.music.api.genres.entity.Genre;
import com.music.api.singer.entity.Singer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
public class Music implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @Setter
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    @Setter
    private Singer singer;

    @OneToMany(mappedBy = "user")
    @Setter
    private Set<Avaliation> avaliations;

    @PrePersist
    private void onCreate() {
        this.date = new Date();
    }
}

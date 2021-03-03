package com.music.api.genres.entity;

import com.music.api.avaliation.entity.Avaliation;
import com.music.api.music.entity.Music;
import com.music.api.singer.entity.Singer;
import com.music.api.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
public class Genre implements Serializable {
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

    @OneToMany(mappedBy = "genre")
    private Set<Music> musics;

    @ManyToMany(mappedBy = "genres")
    private Set<Singer> singers;

    @ManyToMany(mappedBy = "genres")
    private Set<User> users;

    @PrePersist
    private void onCreate() {
        this.date = new Date();
    }
}

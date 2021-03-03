package com.music.api.avaliation.entity;

import com.music.api.music.entity.Music;
import com.music.api.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Avaliation {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @Setter
    private Short score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "music_id")
    @Setter
    private Music music;

    @Column(nullable = false)
    private Date date;

    @PrePersist
    private void onCreate() {
        this.date = new Date();
    }
}

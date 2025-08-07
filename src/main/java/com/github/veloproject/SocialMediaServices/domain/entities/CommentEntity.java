package com.github.veloproject.SocialMediaServices.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Integer id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private PostEntity id_post;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    private LocalDateTime created_at;

}
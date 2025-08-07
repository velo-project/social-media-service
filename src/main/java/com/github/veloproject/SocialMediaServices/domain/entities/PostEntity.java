package com.github.veloproject.SocialMediaServices.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_community", nullable = false)
    private CommunityEntity id_community;

    public boolean community_associate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    private String content;

    private LocalDateTime  created_at;
}

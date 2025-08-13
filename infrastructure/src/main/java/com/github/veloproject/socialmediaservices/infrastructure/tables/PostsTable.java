package com.github.veloproject.socialmediaservices.infrastructure.tables;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "tb_posts"
)
public class PostsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(
            name = "id_post"
    )
    private Integer id;

    @Column(
            name = "content_post",
            nullable = false
    )
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "posted_by",
            updatable = false,
            nullable = false
    )
    private UsersTable postedBy;

    /**
     * Um post pode ou não ser postado em uma comunidade.
     * Se não for postado em uma comunidade, o valor será null.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_community",
            updatable = false,
            nullable = true
    )
    private CommunitiesTable postedIn;

    /**
     * Uma hashtag pode ou não estar presente em um post.
     * Se não estiver presente, o valor será null.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_post_hashtags",
            joinColumns = @JoinColumn(name = "id_post"),
            inverseJoinColumns = @JoinColumn(name = "id_hashtag")
    )
    private Set<HashtagsTable> hashtags;

    @CreationTimestamp
    @Column(
            name = "posted_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime postedAt;

    public PostsTable(Integer id, String content, UsersTable postedBy, CommunitiesTable postedIn, Set<HashtagsTable> hashtags, LocalDateTime postedAt) {
        this.id = id;
        this.content = content;
        this.postedBy = postedBy;
        this.postedIn = postedIn;
        this.hashtags = hashtags;
        this.postedAt = postedAt;
    }
}

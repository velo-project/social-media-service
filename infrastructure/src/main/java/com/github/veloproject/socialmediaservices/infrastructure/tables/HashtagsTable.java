package com.github.veloproject.socialmediaservices.infrastructure.tables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_hashtags")
public class HashtagsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_hashtag")
    private Integer id;

    @ManyToMany(mappedBy = "hashtags", fetch = FetchType.LAZY)
    private Set<PostsTable> posts;

    @Column(
            name = "usage_count_hashtag"
    )
    private Long usageCount;
}

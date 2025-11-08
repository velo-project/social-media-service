package com.github.veloproject.socialmediaservices.infrastructure.tables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_hashtags")
public class HashtagsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hashtag")
    private Integer id;

    @Column(
            name = "tag_hashtag",
            length = 25
    )
    private String tag;

    public HashtagsTable(Integer id, String tag) {
        this.id = id;
        this.tag = tag;
    }
}

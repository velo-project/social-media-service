package com.github.veloproject.SocialMediaServices.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.InvalidRelationTypeException;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HashtagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hashtag")
    private Integer id;

    @Column(unique = true)
    private String hashtag;

    private Integer usage_count;

    private LocalDateTime created_at;
}

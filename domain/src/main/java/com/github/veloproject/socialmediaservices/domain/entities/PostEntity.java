package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class PostEntity {
    private Integer id;
    private String content;
    private String imageUrl;
    private Set<HashtagEntity> hashtags;

    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime postedAt;
    @Setter(AccessLevel.PRIVATE)
    private Integer postedBy;
    @Setter(AccessLevel.PRIVATE)
    private CommunityEntity postedIn;
}

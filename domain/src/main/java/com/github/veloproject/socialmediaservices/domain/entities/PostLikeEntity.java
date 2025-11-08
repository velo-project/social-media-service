package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostLikeEntity {
    private Integer postId;
    private Integer likedBy;
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime likedAt;
}

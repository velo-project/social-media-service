package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserFollowerEntity {
    private Integer userId;
    private Integer followerId;

    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime followedAt;
}

package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommunityMemberEntity {
    private Integer communityId;
    private Integer userId;
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime joinedAt;
}

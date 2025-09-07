package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommunityEntity {
    private Integer id;
    private String name;
    private String description;
    @Setter(AccessLevel.PRIVATE)
    private Integer createdBy;
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;
}

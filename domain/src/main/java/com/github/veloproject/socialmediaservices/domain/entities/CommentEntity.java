package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentEntity {
    private Integer id;
    private Integer postId;
    private Integer commentedBy;
    private String content;
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime commentedAt;
    private Boolean isDeleted;
}

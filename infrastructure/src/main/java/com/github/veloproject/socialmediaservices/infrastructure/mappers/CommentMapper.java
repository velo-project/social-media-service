package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.CommentEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommentsTable;

public class CommentMapper {
    public static CommentsTable toPersistence(CommentEntity e) {
        if (e == null) return null;

        return new CommentsTable(
                e.getId(),
                e.getPostId(),
                e.getCommentedBy(),
                e.getContent(),
                e.getCommentedAt()
        );
    }

    public static CommentEntity toDomain(CommentsTable t) {
        if (t == null) return null;

        return CommentEntity.builder()
                .id(t.getId())
                .postId(t.getPostId())
                .commentedBy(t.getCommentedBy())
                .content(t.getContent())
                .commentedAt(t.getCommentedAt())
                .build();
    }
}

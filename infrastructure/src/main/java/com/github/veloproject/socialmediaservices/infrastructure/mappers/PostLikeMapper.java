package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.PostLikeEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.PostLikesTable;

public class PostLikeMapper {
    public static PostLikesTable toPersistence(PostLikeEntity e) {
        if (e == null) return null;

        PostLikesTable t = new PostLikesTable();
        t.setPostId(e.getPostId());
        t.setLikedBy(e.getLikedBy());
        t.setLikedAt(e.getLikedAt());
        return t;
    }

    public static PostLikeEntity toDomain(PostLikesTable t) {
        if (t == null) return null;

        return PostLikeEntity.builder()
                .postId(t.getPostId())
                .likedBy(t.getLikedBy())
                .likedAt(t.getLikedAt())
                .build();
    }
}

package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.PostsTable;

public class PostMapper {
    public static PostsTable toPersistence(PostEntity e) {
        if (e == null) return null;

        var postedByMapped = UserMapper.toPersistence(e.getPostedBy());
        var postedInMapped = 0; // TODO CommunityMapper
        var getHashtagsMapped = 0; // TODO HashtagMapper

        return new PostsTable(
                e.getId(),
                e.getContent(),
                postedByMapped,
                null,
                null, // Possível problema de rollback?
                e.getPostedAt()
        );
    }

    public static PostEntity toDomain(PostsTable t) {
        if (t == null) return null;

        var postedByMapped = UserMapper.toDomain(t.getPostedBy());
        var postedInMapped = 0; // TODO CommunityMapper
        var getHashtagsMapped = 0; // TODO HashtagMapper

        return PostEntity.builder()
                .id(t.getId())
                .content(t.getContent())
                .hashtags(null)
                .postedIn(null)
                .postedAt(t.getPostedAt())
                .postedBy(postedByMapped)
                .build();
    }
}

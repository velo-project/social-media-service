package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.PostsTable;

public class PostMapper {
    public static PostsTable toPersistence(PostEntity e) {
        if (e == null) return null;

        var postedInMapped = CommunityMapper.toPersistence(e.getPostedIn());
        var getHashtagsMapped = 0; // TODO HashtagMapper

        return new PostsTable(
                e.getId(),
                e.getContent(),
                e.getPostedBy(),
                postedInMapped,
                null // Possível problema de rollback com projeto em produção?
        );
    }

    public static PostEntity toDomain(PostsTable t) {
        if (t == null) return null;

        var postedInMapped = CommunityMapper.toDomain(t.getPostedIn()); // TODO CommunityMapper
        var getHashtagsMapped = 0; // TODO HashtagMapper

        return PostEntity.builder()
                .id(t.getId())
                .content(t.getContent())
                .hashtags(null)
                .postedIn(postedInMapped)
                .postedAt(t.getPostedAt())
                .postedBy(t.getPostedBy())
                .build();
    }
}

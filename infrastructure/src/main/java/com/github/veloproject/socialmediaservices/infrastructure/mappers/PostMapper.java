package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.PostsTable;

import java.util.stream.Collectors;

public class PostMapper {
    public static PostsTable toPersistence(PostEntity e) {
        if (e == null) return null;

        var postedInMapped = CommunityMapper.toPersistence(e.getPostedIn());
        var hashtagsMapped = e.getHashtags()
                .stream()
                .map(HashtagMapper::toPersistence)
                .collect(Collectors.toSet());

        var table = new PostsTable(
                e.getId(),
                e.getContent(),
                e.getPostedBy(),
                postedInMapped,
                hashtagsMapped
        );
        table.setIsDeleted(e.getIsDeleted());

        return table;
    }

    public static PostEntity toDomain(PostsTable t) {
        if (t == null) return null;

        var postedInMapped = CommunityMapper.toDomain(t.getPostedIn());
        var hashtagsMapped = t.getHashtags()
                .stream()
                .map(HashtagMapper::toDomain)
                .collect(Collectors.toSet());

        return PostEntity.builder()
                .id(t.getId())
                .content(t.getContent())
                .postedIn(postedInMapped)
                .postedAt(t.getPostedAt())
                .postedBy(t.getPostedBy())
                .hashtags(hashtagsMapped)
                .isDeleted(t.getIsDeleted())
                .build();
    }
}

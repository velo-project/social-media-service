package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.UserFollowersTable;

public class UserFollowerMapper {
    public static UserFollowersTable toPersistence(UserFollowerEntity e) {
        if (e == null) return null;

        return new UserFollowersTable(
                e.getUserId(),
                e.getFollowerId(),
                e.getFollowedAt()
        );
    }

    public static UserFollowerEntity toDomain(UserFollowersTable t) {
        if (t == null) return null;

        return UserFollowerEntity.builder()
                .userId(t.getUserId())
                .followerId(t.getFollowerId())
                .followedAt(t.getFollowedAt())
                .build();
    }
}

package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.UsersTable;

public class UserMapper {
    public static UsersTable toPersistence(UserEntity e) {
        if (e == null) return null;

        return new UsersTable(
                e.getId(),
                e.getName(),
                e.getNickname(),
                e.getBannerPhotoUrl(),
                e.getProfilePhotoUrl(),
                e.getIsBlocked(),
                e.getIsDeleted(),
                e.getRegisteredAt()
        );
    }

    public static UserEntity toDomain(UsersTable t) {
        if (t == null) return null;

        return UserEntity.builder()
                .id(t.getId())
                .name(t.getName())
                .nickname(t.getNickname())
                .bannerPhotoUrl(t.getBannerPhotoUrl())
                .profilePhotoUrl(t.getProfilePhotoUrl())
                .isBlocked(t.getIsBlocked())
                .isDeleted(t.getIsDeleted())
                .registeredAt(t.getRegisteredAt())
                .build();
    }
}

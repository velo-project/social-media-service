package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunitiesTable;

public class CommunityMapper {
    public static CommunitiesTable toPersistence(CommunityEntity e) {
        if (e == null) return null;
        var createdBy = UserMapper.toPersistence(e.getCreatedBy());

        return new CommunitiesTable(
                e.getId(),
                e.getName(),
                e.getDescription(),
                createdBy,
                e.getCreatedAt()
        );
    }

    public static CommunityEntity toDomain(CommunitiesTable t) {
        if (t == null) return null;
        var createdBy = UserMapper.toDomain(t.getCreatedBy());

        return CommunityEntity.builder()
                .id(t.getId())
                .name(t.getName())
                .description(t.getDescription())
                .createdBy(createdBy)
                .createdAt(t.getCreatedAt())
                .build();
    }
}

package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunitiesTable;

public class CommunityMapper {
    public static CommunitiesTable toPersistence(CommunityEntity e) {
        if (e == null) return null;

        return new CommunitiesTable(
                e.getId(),
                e.getName(),
                e.getDescription(),
                e.getCreatedBy(),
                e.getCreatedAt()
        );
    }

    public static CommunityEntity toDomain(CommunitiesTable t) {
        if (t == null) return null;

        return CommunityEntity.builder()
                .id(t.getId())
                .name(t.getName())
                .description(t.getDescription())
                .createdBy(t.getCreatedBy())
                .createdAt(t.getCreatedAt())
                .build();
    }
}

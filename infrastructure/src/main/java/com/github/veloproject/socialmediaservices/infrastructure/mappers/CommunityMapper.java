package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.application.dto.CommunityDto;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunitiesTable;

public class CommunityMapper {
    public static CommunitiesTable toPersistence(CommunityEntity e) {
        if (e == null) return null;

        return new CommunitiesTable(
                e.getId(),
                e.getName(),
                e.getDescription(),
                e.getEmbeddings(),
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
                .embeddings(t.getEmbeddings())
                .createdBy(t.getCreatedBy())
                .createdAt(t.getCreatedAt())
                .build();
    }

    public static CommunityDto toDto(CommunitiesTable t) {
        if (t == null) return null;

        return new CommunityDto(
                t.getId(),
                t.getName(),
                t.getDescription(),
                t.getCreatedBy(),
                t.getCreatedAt()
        );
    }
}

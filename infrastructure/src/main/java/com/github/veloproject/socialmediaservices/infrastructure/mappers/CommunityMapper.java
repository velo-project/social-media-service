package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.application.dto.CommunityDto;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunitiesTable;

public class CommunityMapper {
    public static CommunitiesTable toPersistence(CommunityEntity e) {
        if (e == null) return null;

        var community = new CommunitiesTable(
                e.getId(),
                e.getName(),
                e.getDescription(),
                e.getPhotoUrl(),
                e.getBannerUrl(),
                e.getEmbeddings(),
                e.getCreatedBy(),
                e.getCreatedAt()
        );
        e.setIsDeleted(e.getIsDeleted());

        return community;
    }

    public static CommunityEntity toDomain(CommunitiesTable t) {
        if (t == null) return null;

        return CommunityEntity.builder()
                .id(t.getId())
                .name(t.getName())
                .description(t.getDescription())
                .photoUrl(t.getPhotoUrl())
                .bannerUrl(t.getBannerUrl())
                .embeddings(t.getEmbeddings())
                .createdBy(t.getCreatedBy())
                .createdAt(t.getCreatedAt())
                .isDeleted(t.getIsDeleted())
                .build();
    }

    public static CommunityDto toDto(CommunitiesTable t) {
        if (t == null) return null;

        return new CommunityDto(
                t.getId(),
                t.getName(),
                t.getPhotoUrl(),
                t.getBannerUrl(),
                t.getDescription(),
                t.getCreatedBy(),
                t.getCreatedAt()
        );
    }
}

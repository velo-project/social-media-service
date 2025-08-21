package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunitiesTable;

public class CommunityMapper {
    public static CommunitiesTable toPersistence(CommunityEntity e) {
        if (e == null) return null;

        return new CommunitiesTable(
                null,
                null,
                null,
                null,
                null
        );
    }

    public static CommunityEntity toDomain(CommunitiesTable t) {
        if (t == null) return null;

        return null;
    }
}

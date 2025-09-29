package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.HashtagEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.HashtagsTable;

public class HashtagMapper {
    public static HashtagsTable toPersistence(HashtagEntity entity) {
        if (entity == null) return null;

        return new HashtagsTable(
                entity.getId(),
                entity.getTag()
        );
    }

    public static HashtagEntity toDomain(HashtagsTable table) {
        if (table == null) return null;

        return HashtagEntity.builder()
                .id(table.getId())
                .tag(table.getTag())
                .build();
    }
}

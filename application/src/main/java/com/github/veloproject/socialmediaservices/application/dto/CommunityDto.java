package com.github.veloproject.socialmediaservices.application.dto;

import java.time.LocalDateTime;

public record CommunityDto(
        Integer id,
        String name,
        String description,
        Integer createdBy,
        LocalDateTime createdAt
) {
}

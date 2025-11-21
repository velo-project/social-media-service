package com.github.veloproject.socialmediaservices.application.dto;

import java.time.LocalDateTime;

public record CommunityDto(
        Integer id,
        String name,
        String photoUrl,
        String bannerUrl,
        String description,
        Integer createdBy,
        LocalDateTime createdAt
) {
}

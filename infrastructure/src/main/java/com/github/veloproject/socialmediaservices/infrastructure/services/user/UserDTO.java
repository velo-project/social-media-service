package com.github.veloproject.socialmediaservices.infrastructure.services.user;

public record UserDTO(
        Integer id,
        String name,
        String nickname,
        String bannerPhotoUrl,
        String profilePhotoUrl,
        Boolean isBlocked,
        Boolean isDeleted
) {}
package com.github.veloproject.socialmediaservices.application.dto;

public record UserInfo(
        Integer id,
        String name,
        String nickname,
        String bannerPhotoUrl,
        String profilePhotoUrl,
        Boolean isBlocked,
        Boolean isDeleted
) {}
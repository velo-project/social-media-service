package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
public class UserEntity {
    private Integer id;
    private String name;
    private String nickname;
    private String bannerPhotoUrl;
    private String profilePhotoUrl;
    private Boolean isBlocked;
    private Boolean isDeleted;
    private LocalDateTime registeredAt;
}

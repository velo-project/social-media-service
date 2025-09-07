package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;

import java.util.Optional;

public interface ICommunityRepository {
    Optional<CommunityEntity> findById(Integer id);
}

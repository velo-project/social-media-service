package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.HashtagEntity;

import java.util.Optional;

public interface IHashtagRepository {
    HashtagEntity save(HashtagEntity hashtag);
    boolean existsByTag(String tag);
    Optional<HashtagEntity> findByTag(String tag);
}

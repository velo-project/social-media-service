package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.IHashtagRepository;
import com.github.veloproject.socialmediaservices.domain.entities.HashtagEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.HashtagMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.IHashtagRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HashtagRepositoryImp implements IHashtagRepository {
    private final IHashtagRepositoryJpa jpa;

    public HashtagRepositoryImp(IHashtagRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public HashtagEntity save(HashtagEntity hashtagEntity) {
        var hashtag = HashtagMapper
                .toPersistence(hashtagEntity);

        return HashtagMapper
                .toDomain(jpa.save(hashtag));
    }

    @Override
    public boolean existsByTag(String tag) {
        return jpa
                .existsByTag(tag);
    }

    @Override
    public Optional<HashtagEntity> findByTag(String tag) {
        return jpa
                .findByTag(tag);
    }
}

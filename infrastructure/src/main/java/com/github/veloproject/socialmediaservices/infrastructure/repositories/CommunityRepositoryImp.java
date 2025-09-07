package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.CommunityMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.ICommunityRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommunityRepositoryImp implements ICommunityRepository {
    private final ICommunityRepositoryJpa jpa;

    public CommunityRepositoryImp(ICommunityRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<CommunityEntity> findById(Integer id) {
        var user = jpa.findById(id);

        return user.map(CommunityMapper::toDomain);
    }
}

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
        var community = jpa.findById(id);

        return community.map(CommunityMapper::toDomain);
    }

    @Override
    public boolean existsById(Integer id) {
        return jpa.existsById(id);
    }

    @Override
    public Integer save(CommunityEntity communityEntity) {
        var community = CommunityMapper
                .toPersistence(communityEntity);

        return jpa.save(community)
                .getId();
    }

    @Override
    public void deleteById(Integer id) {
        jpa.deleteById(id);
    }
}

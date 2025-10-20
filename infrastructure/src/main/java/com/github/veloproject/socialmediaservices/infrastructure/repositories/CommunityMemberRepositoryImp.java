package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.CommunityMemberMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.ICommunityMemberRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommunityMemberRepositoryImp implements ICommunityMemberRepository {
    private final ICommunityMemberRepositoryJpa jpa;

    public CommunityMemberRepositoryImp(ICommunityMemberRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(CommunityMemberEntity entity) {
        var table = CommunityMemberMapper.toPersistence(entity);
        jpa.save(table);
    }

    @Override
    public void delete(Integer communityId, Integer userId) {
        jpa.deleteByCommunityIdAndUserId(communityId, userId);
    }

    @Override
    public boolean existsMember(Integer communityId, Integer userId) {
        return jpa.existsByCommunityIdAndUserId(communityId, userId);
    }

    @Override
    public long countMembers(Integer communityId) {
        return jpa.countByCommunityId(communityId);
    }

    @Override
    public Page<CommunityMemberEntity> findByCommunityId(Integer communityId, Pageable pageable) {
        return jpa.findByCommunityId(communityId, pageable)
                .map(CommunityMemberMapper::toDomain);
    }

    @Override
    public Optional<CommunityMemberEntity> findMember(Integer communityId, Integer userId) {
        return jpa.findByCommunityIdAndUserId(communityId, userId)
                .map(CommunityMemberMapper::toDomain);
    }
}

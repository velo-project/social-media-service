package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICommunityMemberRepository {
    void save(CommunityMemberEntity entity);
    void delete(Integer communityId, Integer userId);
    boolean existsMember(Integer communityId, Integer userId);
    long countMembers(Integer communityId);
    Page<CommunityMemberEntity> findMembersByCommunityId(Integer communityId, Pageable pageable);
    Optional<CommunityMemberEntity> findMember(Integer communityId, Integer userId);
}

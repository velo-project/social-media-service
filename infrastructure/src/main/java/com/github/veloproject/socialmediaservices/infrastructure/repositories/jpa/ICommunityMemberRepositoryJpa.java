package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.domain.valueObjects.CommunityMemberId;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunityMembersTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommunityMemberRepositoryJpa extends JpaRepository<CommunityMembersTable, CommunityMemberId> {
    void deleteByCommunityIdAndUserId(Integer communityId, Integer userId);
    boolean existsByCommunityIdAndUserId(Integer communityId, Integer userId);
    long countByCommunityId(Integer communityId);
    Page<CommunityMembersTable> findByCommunityId(Integer communityId, Pageable pageable);
    Optional<CommunityMembersTable> findByCommunityIdAndUserId(Integer communityId, Integer userId);
}

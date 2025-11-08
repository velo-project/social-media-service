package com.github.veloproject.socialmediaservices.infrastructure.mappers;

import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunityMembersTable;

public class CommunityMemberMapper {
    public static CommunityMembersTable toPersistence(CommunityMemberEntity e) {
        if (e == null) return null;

        var communityMember= new CommunityMembersTable();
        communityMember.setCommunityId(e.getCommunityId());
        communityMember.setUserId(e.getUserId());
        communityMember.setJoinedAt(e.getJoinedAt());

        return communityMember;
    }

    public static CommunityMemberEntity toDomain(CommunityMembersTable t) {
        if (t == null) return null;

        return CommunityMemberEntity.builder()
                .communityId(t.getCommunityId())
                .userId(t.getUserId())
                .joinedAt(t.getJoinedAt())
                .build();
    }
}

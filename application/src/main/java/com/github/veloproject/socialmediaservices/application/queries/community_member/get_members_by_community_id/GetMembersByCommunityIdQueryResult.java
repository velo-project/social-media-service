package com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class GetMembersByCommunityIdQueryResult extends Response {
    private final Page<CommunityMemberEntity> members;

    public GetMembersByCommunityIdQueryResult(Integer statusCode, Page<CommunityMemberEntity> members) {
        super(statusCode);
        this.members = members;
    }
}

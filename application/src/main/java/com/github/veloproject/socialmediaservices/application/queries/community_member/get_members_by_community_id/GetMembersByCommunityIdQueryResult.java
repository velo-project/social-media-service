package com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class GetMembersByCommunityIdQueryResult extends Response {
    private final List<UserInfo> members;

    public GetMembersByCommunityIdQueryResult(Integer statusCode, List<UserInfo> members) {
        super(statusCode);
        this.members = members;
    }
}

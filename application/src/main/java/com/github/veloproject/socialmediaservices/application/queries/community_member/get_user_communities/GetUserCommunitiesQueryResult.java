package com.github.veloproject.socialmediaservices.application.queries.community_member.get_user_communities;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetUserCommunitiesQueryResult extends Response {
    private final List<Integer> communityIds;
    public GetUserCommunitiesQueryResult(Integer statusCode, String message,  List<Integer> communityIds) {
        super(statusCode, message);
        this.communityIds = communityIds;
    }
}

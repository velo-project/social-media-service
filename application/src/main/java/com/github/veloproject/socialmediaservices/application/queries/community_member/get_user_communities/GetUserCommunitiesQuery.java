package com.github.veloproject.socialmediaservices.application.queries.community_member.get_user_communities;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetUserCommunitiesQuery(
        String nickname
) implements Request<GetUserCommunitiesQueryResult> {
}

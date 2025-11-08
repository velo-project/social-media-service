package com.github.veloproject.socialmediaservices.application.queries.community.get_community_by_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetCommunityByIdQuery(
        Integer communityId
) implements Request<GetCommunityByIdQueryResult> {
}

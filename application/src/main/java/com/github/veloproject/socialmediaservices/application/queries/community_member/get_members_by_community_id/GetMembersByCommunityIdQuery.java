package com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetMembersByCommunityIdQuery(
        Integer communityId,
        Integer page
) implements Request<GetMembersByCommunityIdQueryResult> {
}

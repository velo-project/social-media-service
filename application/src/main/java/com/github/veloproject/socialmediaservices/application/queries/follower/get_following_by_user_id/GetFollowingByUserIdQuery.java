package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetFollowingByUserIdQuery(
        Integer userId
) implements Request<GetFollowingByUserIdQueryResult> {
}

package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetFollowersByUserIdQuery(
        Integer userId
) implements Request<GetFollowersByUserIdQueryResult> {
}

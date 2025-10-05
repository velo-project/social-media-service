package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class GetFollowingByUserIdQueryResult extends Response {
    public GetFollowingByUserIdQueryResult(Integer statusCode) {
        super(statusCode);
    }
}

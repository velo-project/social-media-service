package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class GetFollowersByUserIdQueryResult extends Response {
    public GetFollowersByUserIdQueryResult(Integer statusCode) {
        super(statusCode);
    }
}

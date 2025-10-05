package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.handler;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.GetFollowingByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.GetFollowingByUserIdQueryResult;
import org.springframework.stereotype.Service;

@Service
public class GetFollowingByUserIdQueryHandler extends NoAuthRequestHandler<GetFollowingByUserIdQuery, GetFollowingByUserIdQueryResult> {
    @Override
    public GetFollowingByUserIdQueryResult handle(GetFollowingByUserIdQuery request) {
        return null;
    }
}

package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.handler;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.GetFollowersByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.GetFollowersByUserIdQueryResult;
import org.springframework.stereotype.Service;

@Service
public class GetFollowersByUserIdQueryHandler extends NoAuthRequestHandler<GetFollowersByUserIdQuery, GetFollowersByUserIdQueryResult> {
    @Override
    public GetFollowersByUserIdQueryResult handle(GetFollowersByUserIdQuery request) {
        return null;
    }
}

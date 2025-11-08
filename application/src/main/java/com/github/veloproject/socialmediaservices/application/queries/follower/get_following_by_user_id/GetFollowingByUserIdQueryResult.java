package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFollowingByUserIdQueryResult extends Response {
    private final List<UserFollowerEntity> following;

    public GetFollowingByUserIdQueryResult(Integer statusCode, List<UserFollowerEntity> following) {
        super(statusCode);
        this.following = following;
    }
}

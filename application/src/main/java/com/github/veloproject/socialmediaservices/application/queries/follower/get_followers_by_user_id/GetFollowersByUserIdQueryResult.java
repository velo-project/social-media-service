package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFollowersByUserIdQueryResult extends Response {
    private final List<UserFollowerEntity> followers;

    public GetFollowersByUserIdQueryResult(Integer statusCode,
                                           List<UserFollowerEntity> followers) {
        super(statusCode);
        this.followers = followers;
    }
}

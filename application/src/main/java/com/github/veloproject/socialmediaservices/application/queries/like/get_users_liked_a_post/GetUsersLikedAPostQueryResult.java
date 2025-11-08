package com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostLikeEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetUsersLikedAPostQueryResult extends Response {
    private final List<PostLikeEntity> users;

    public GetUsersLikedAPostQueryResult(Integer statusCode, List<PostLikeEntity> users) {
        super(statusCode);
        this.users = users;
    }
}

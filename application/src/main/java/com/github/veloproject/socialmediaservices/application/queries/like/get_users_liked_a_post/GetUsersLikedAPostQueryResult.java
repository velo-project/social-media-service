package com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostLikeEntity;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class GetUsersLikedAPostQueryResult extends Response {
    private final Page<PostLikeEntity> users;

    public GetUsersLikedAPostQueryResult(Integer statusCode, Page<PostLikeEntity> users) {
        super(statusCode);
        this.users = users;
    }
}

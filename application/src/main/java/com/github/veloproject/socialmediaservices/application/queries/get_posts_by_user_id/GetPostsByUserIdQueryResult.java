package com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPostsByUserIdQueryResult extends Response {
    private final List<PostEntity> posts;

    public GetPostsByUserIdQueryResult(List<PostEntity> posts, Integer statusCode) {
        super(statusCode);
        this.posts = posts;
    }
}

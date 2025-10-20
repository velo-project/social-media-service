package com.github.veloproject.socialmediaservices.application.queries.post.get_posts_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPostsByUserIdQueryResult extends Response {
    private final List<PostEntity> posts;

    public GetPostsByUserIdQueryResult(Integer statusCode, List<PostEntity> posts) {
        super(statusCode);
        this.posts = posts;
    }
}

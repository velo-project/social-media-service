package com.github.veloproject.socialmediaservices.application.queries.post.get_post_by_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import lombok.Getter;

@Getter
public class GetPostByIdQueryResult extends Response {
    private final PostEntity post;

    public GetPostByIdQueryResult(Integer statusCode, String message, PostEntity post) {
        super(statusCode, message);
        this.post = post;
    }
}

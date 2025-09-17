package com.github.veloproject.socialmediaservices.application.queries.get_post_by_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;

public class GetPostByIdQueryResult extends Response {
    private final PostEntity post;
    public GetPostByIdQueryResult(PostEntity post, Integer statusCode, String message) {
        super(statusCode, message);
        this.post = post;
    }
}

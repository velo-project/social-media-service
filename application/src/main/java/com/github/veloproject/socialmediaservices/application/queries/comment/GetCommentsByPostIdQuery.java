package com.github.veloproject.socialmediaservices.application.queries.comment;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetCommentsByPostIdQuery(
        Integer postId,
        Integer page
) implements Request<GetCommentsByPostIdQueryResult> {
}

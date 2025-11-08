package com.github.veloproject.socialmediaservices.application.queries.post.get_post_by_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetPostByIdQuery(Integer id) implements Request<GetPostByIdQueryResult> {
}

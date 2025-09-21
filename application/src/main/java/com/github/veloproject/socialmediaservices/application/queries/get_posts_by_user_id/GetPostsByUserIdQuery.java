package com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetPostsByUserIdQuery(Integer userId, Integer page) implements Request<GetPostsByUserIdQueryResult> {
}

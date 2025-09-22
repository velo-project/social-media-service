package com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetUsersLikedAPostQuery(Integer postId, Integer page) implements Request<GetUsersLikedAPostQueryResult> {

}

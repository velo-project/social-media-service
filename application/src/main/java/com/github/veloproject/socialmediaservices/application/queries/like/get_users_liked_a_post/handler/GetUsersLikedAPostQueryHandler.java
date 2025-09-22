package com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostLikeRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post.GetUsersLikedAPostQuery;
import com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post.GetUsersLikedAPostQueryResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GetUsersLikedAPostQueryHandler extends NoAuthRequestHandler<GetUsersLikedAPostQuery, GetUsersLikedAPostQueryResult> {
    private final IPostLikeRepository postlikeRepository;

    public GetUsersLikedAPostQueryHandler(IPostLikeRepository postlikeRepository) {
        this.postlikeRepository = postlikeRepository;
    }

    @Override
    public GetUsersLikedAPostQueryResult handle(GetUsersLikedAPostQuery request) {
        var pageRequest = PageRequest.of(
                0,
                request.page(),
                Sort.by("likedAt")
                        .descending());
        var users = postlikeRepository.findLikesByPost(request.postId(), pageRequest);

        return new GetUsersLikedAPostQueryResult(200, users);
    }
}

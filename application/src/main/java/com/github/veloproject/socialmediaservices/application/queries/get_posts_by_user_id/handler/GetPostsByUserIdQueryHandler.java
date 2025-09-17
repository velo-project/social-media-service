package com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id.GetPostsByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id.GetPostsByUserIdQueryResult;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class GetPostsByUserIdQueryHandler extends NoAuthRequestHandler<GetPostsByUserIdQuery, GetPostsByUserIdQueryResult> {
    private final IPostRepository postRepository;

    public GetPostsByUserIdQueryHandler(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public GetPostsByUserIdQueryResult handle(GetPostsByUserIdQuery request) {
        var posts = postRepository.findAllByUserId(request.getUserId());

        return new GetPostsByUserIdQueryResult(posts,200);
    }

}

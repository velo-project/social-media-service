package com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id.GetPostsByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id.GetPostsByUserIdQueryResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        var pageRequest = PageRequest.of(0, request.page(), Sort.by("postedIn").descending());
        var posts = postRepository.findPageByUserId(request.userId(), pageRequest);

        return new GetPostsByUserIdQueryResult(200, posts);
    }

}

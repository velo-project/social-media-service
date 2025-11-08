package com.github.veloproject.socialmediaservices.application.queries.feed.get_recommended_feed.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.feed.get_recommended_feed.GetRecommendedFeedQuery;
import com.github.veloproject.socialmediaservices.application.queries.feed.get_recommended_feed.GetRecommendedFeedQueryResult;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class GetRecommendedFeedQueryHandler extends AuthRequestHandler<GetRecommendedFeedQuery, GetRecommendedFeedQueryResult> {
    private final IPostRepository postRepository;

    public GetRecommendedFeedQueryHandler(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public GetRecommendedFeedQueryResult handle(GetRecommendedFeedQuery request, JwtAuthenticationToken token) {
        var userId = Integer.valueOf(token.getToken().getSubject());
        var feed =  postRepository.findRecommendedFeed(userId);

        return new GetRecommendedFeedQueryResult(200, feed);
    }
}

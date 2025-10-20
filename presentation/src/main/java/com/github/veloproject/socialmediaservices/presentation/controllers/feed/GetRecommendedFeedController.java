package com.github.veloproject.socialmediaservices.presentation.controllers.feed;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.feed.get_recommended_feed.GetRecommendedFeedQuery;
import com.github.veloproject.socialmediaservices.application.queries.feed.get_recommended_feed.GetRecommendedFeedQueryResult;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/feeds")
public class GetRecommendedFeedController {
    private final LoggingMediatorImp mediator;

    public GetRecommendedFeedController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/feed")
    public ResponseEntity<GetRecommendedFeedQueryResult> getRecommendedFeed(
            JwtAuthenticationToken token
    ) {
        var query = new GetRecommendedFeedQuery();
        var response = mediator.send(query, token);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

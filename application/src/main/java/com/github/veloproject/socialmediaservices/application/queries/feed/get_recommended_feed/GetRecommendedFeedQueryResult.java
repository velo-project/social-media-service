package com.github.veloproject.socialmediaservices.application.queries.feed.get_recommended_feed;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetRecommendedFeedQueryResult extends Response {
    private final List<PostEntity> feed;

    public GetRecommendedFeedQueryResult(Integer statusCode, List<PostEntity> feed) {
        super(statusCode);
        this.feed = feed;
    }
}

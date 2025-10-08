package com.github.veloproject.socialmediaservices.application.queries.community.get_communities_by_similarity;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record FilterCommunitiesBySimilarityQuery(
        String query
) implements Request<FilterCommunitiesBySimilarityQueryResult> {
}

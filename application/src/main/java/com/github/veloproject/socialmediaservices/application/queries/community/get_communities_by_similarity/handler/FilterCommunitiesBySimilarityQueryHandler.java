package com.github.veloproject.socialmediaservices.application.queries.community.get_communities_by_similarity.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IGeminiAPIService;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.community.get_communities_by_similarity.FilterCommunitiesBySimilarityQuery;
import com.github.veloproject.socialmediaservices.application.queries.community.get_communities_by_similarity.FilterCommunitiesBySimilarityQueryResult;
import org.springframework.stereotype.Service;

@Service
public class FilterCommunitiesBySimilarityQueryHandler extends NoAuthRequestHandler<FilterCommunitiesBySimilarityQuery, FilterCommunitiesBySimilarityQueryResult> {
    private final ICommunityRepository communityRepository;
    private final IGeminiAPIService geminiAPIService;

    public FilterCommunitiesBySimilarityQueryHandler(ICommunityRepository communityRepository,
                                                     IGeminiAPIService geminiAPIService) {
        this.communityRepository = communityRepository;
        this.geminiAPIService = geminiAPIService;
    }

    @Override
    public FilterCommunitiesBySimilarityQueryResult handle(FilterCommunitiesBySimilarityQuery query) {
        float[] queryEmbedding = geminiAPIService.generateEmbeddings(query.query());

        var communities = communityRepository
                .findSimilarByEmbedding(queryEmbedding, 0.3f);

        return new FilterCommunitiesBySimilarityQueryResult(200, communities);
    }
}

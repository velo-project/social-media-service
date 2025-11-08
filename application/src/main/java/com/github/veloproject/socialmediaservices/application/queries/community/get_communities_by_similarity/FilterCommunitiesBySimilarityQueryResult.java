package com.github.veloproject.socialmediaservices.application.queries.community.get_communities_by_similarity;

import com.github.veloproject.socialmediaservices.application.dto.CommunityDto;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import lombok.Getter;

import java.util.List;

@Getter
public class FilterCommunitiesBySimilarityQueryResult extends Response {
    private final List<CommunityDto> communities;

    public FilterCommunitiesBySimilarityQueryResult(Integer statusCode, List<CommunityDto> communities) {
        super(statusCode);
        this.communities = communities;
    }
}

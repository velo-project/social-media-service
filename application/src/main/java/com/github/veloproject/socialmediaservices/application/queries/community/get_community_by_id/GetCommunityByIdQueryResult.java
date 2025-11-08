package com.github.veloproject.socialmediaservices.application.queries.community.get_community_by_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import lombok.Getter;

@Getter
public class GetCommunityByIdQueryResult extends Response {
    private final CommunityEntity community;

    public GetCommunityByIdQueryResult(Integer statusCode, CommunityEntity community) {
        super(statusCode);
        this.community = community;
    }
}

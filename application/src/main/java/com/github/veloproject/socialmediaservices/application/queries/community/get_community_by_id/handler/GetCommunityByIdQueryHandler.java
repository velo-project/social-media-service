package com.github.veloproject.socialmediaservices.application.queries.community.get_community_by_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.community.get_community_by_id.GetCommunityByIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.community.get_community_by_id.GetCommunityByIdQueryResult;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidCommunityProvidedException;
import org.springframework.stereotype.Service;

@Service
public class GetCommunityByIdQueryHandler extends NoAuthRequestHandler<GetCommunityByIdQuery, GetCommunityByIdQueryResult> {
    private final ICommunityRepository communityRepository;

    public GetCommunityByIdQueryHandler(ICommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public GetCommunityByIdQueryResult handle(GetCommunityByIdQuery request) {
        var community = communityRepository
                .findById(request.communityId())
                .orElseThrow(InvalidCommunityProvidedException::new);

        return new GetCommunityByIdQueryResult(200, community);
    }
}

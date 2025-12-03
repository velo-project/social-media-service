package com.github.veloproject.socialmediaservices.application.queries.community_member.get_user_communities.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_user_communities.GetUserCommunitiesQuery;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_user_communities.GetUserCommunitiesQueryResult;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserCommunitiesQueryHandler extends NoAuthRequestHandler<GetUserCommunitiesQuery, GetUserCommunitiesQueryResult> {
    private final ICommunityMemberRepository communityMemberRepository;
    private final IUserGRPCClient userGRPCClient;

    public GetUserCommunitiesQueryHandler(ICommunityMemberRepository communityMemberRepository, IUserGRPCClient userGRPCClient) {
        this.communityMemberRepository = communityMemberRepository;
        this.userGRPCClient = userGRPCClient;
    }

    @Override
    public GetUserCommunitiesQueryResult handle(GetUserCommunitiesQuery request) {
        var user = Optional.ofNullable(userGRPCClient.getUserByNickname(request.nickname())).orElseThrow(InvalidUserProvidedException::new);

        var memberships = communityMemberRepository.findByUserId(user.id());

        var communityIds = memberships.stream()
                .map(CommunityMemberEntity::getCommunityId)
                .toList();

        return new GetUserCommunitiesQueryResult(200, "Comunidades do usuário listadas.", communityIds);
    }
}

package com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.GetMembersByCommunityIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.GetMembersByCommunityIdQueryResult;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetMembersByCommunityIdQueryHandler extends NoAuthRequestHandler<GetMembersByCommunityIdQuery, GetMembersByCommunityIdQueryResult> {
    private final ICommunityMemberRepository communityMemberRepository;
    private final IUserGRPCClient userGRPCClient;

    public GetMembersByCommunityIdQueryHandler(ICommunityMemberRepository communityMemberRepository,
                                               IUserGRPCClient userGRPCClient) {
        this.communityMemberRepository = communityMemberRepository;
        this.userGRPCClient = userGRPCClient;
    }

    @Override
    public GetMembersByCommunityIdQueryResult handle(GetMembersByCommunityIdQuery request) {
        var pageRequest = PageRequest.of(
                request.page(),
                20);
        var cm = communityMemberRepository.findByCommunityId(request.communityId(), pageRequest)
                .stream()
                .map(CommunityMemberEntity::getUserId)
                .toList();
        var members = userGRPCClient.getUsersByIdList(cm);

        return new GetMembersByCommunityIdQueryResult(200, members);
    }
}

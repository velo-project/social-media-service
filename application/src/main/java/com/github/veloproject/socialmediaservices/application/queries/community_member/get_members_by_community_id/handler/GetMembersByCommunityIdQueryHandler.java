package com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.GetMembersByCommunityIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.GetMembersByCommunityIdQueryResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GetMembersByCommunityIdQueryHandler extends NoAuthRequestHandler<GetMembersByCommunityIdQuery, GetMembersByCommunityIdQueryResult> {
    private final ICommunityMemberRepository communityMemberRepository;

    public GetMembersByCommunityIdQueryHandler(ICommunityMemberRepository communityMemberRepository) {
        this.communityMemberRepository = communityMemberRepository;
    }

    @Override
    public GetMembersByCommunityIdQueryResult handle(GetMembersByCommunityIdQuery request) {
        var pageRequest = PageRequest.of(
                request.page(),
                20,
                Sort.by("joinedAt")
                        .descending());
        var members = communityMemberRepository.findMembersByCommunityId(request.communityId(), pageRequest);
        return new GetMembersByCommunityIdQueryResult(200, members);
    }
}

package com.github.veloproject.socialmediaservices.application.commands.community_member.leave_community.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
import com.github.veloproject.socialmediaservices.application.commands.community_member.leave_community.LeaveCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community_member.leave_community.LeaveCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LeaveCommunityCommandHandler extends AuthRequestHandler<LeaveCommunityCommand, LeaveCommunityCommandResult> {
    private final ICommunityRepository communityRepository;
    private final ICommunityMemberRepository communityMemberRepository;
    private final IUserServices userServices;

    public LeaveCommunityCommandHandler(ICommunityRepository communityRepository, ICommunityMemberRepository communityMemberRepository, IUserServices userServices) {
        this.communityRepository = communityRepository;
        this.communityMemberRepository = communityMemberRepository;
        this.userServices = userServices;
    }

    @Transactional
    @Override
    public LeaveCommunityCommandResult handle(LeaveCommunityCommand request,
                                              JwtAuthenticationToken token) {
        var integerSubject = Integer.valueOf(token.getToken().getSubject());
        var community = communityRepository.findById(request.communityId());

        if (!userServices
                .existsById(integerSubject)) throw new InvalidUserProvidedException();

        else if (community
                .isEmpty()) throw new InvalidCommunityProvidedException();

        else if (community.get()
                .getCreatedBy()
                .equals(integerSubject)) throw new UserIsTheCommunityOwnerException();

        else if (!communityMemberRepository
                .existsMember(integerSubject, request.communityId())) throw new UserNotInCommunityException();

        communityMemberRepository.delete(request.communityId(), integerSubject);
        return new LeaveCommunityCommandResult(200);
    }
}

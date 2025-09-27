package com.github.veloproject.socialmediaservices.application.commands.community_member.join_community.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
import com.github.veloproject.socialmediaservices.application.commands.community_member.join_community.JoinCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community_member.join_community.JoinCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidCommunityProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserAlreadyInCommunityException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JoinCommunityCommandHandler extends AuthRequestHandler<JoinCommunityCommand, JoinCommunityCommandResult> {
    private final ICommunityMemberRepository communityMemberRepository;
    private final ICommunityRepository communityRepository;
    private final IUserServices  userServices;

    public JoinCommunityCommandHandler(ICommunityMemberRepository communityMemberRepository, ICommunityRepository communityRepository, IUserServices userServices) {
        this.communityMemberRepository = communityMemberRepository;
        this.communityRepository = communityRepository;
        this.userServices = userServices;
    }

    @Override
    public JoinCommunityCommandResult handle(JoinCommunityCommand request,
                                             JwtAuthenticationToken token) {
        var integerSubject = Integer.valueOf(token.getToken().getSubject());
        if (!userServices
                .existsById(integerSubject)) throw new InvalidUserProvidedException();
        else if (!communityRepository
                .existsById(request.communityId())) throw new InvalidCommunityProvidedException();
        else if (communityMemberRepository
                .existsMember(integerSubject, request.communityId())) throw new UserAlreadyInCommunityException();

        var communityMember = CommunityMemberEntity.builder()
                        .communityId(request.communityId())
                        .userId(integerSubject)
                        .build();
        communityMemberRepository.save(communityMember);

        return new JoinCommunityCommandResult(200);
    }
}

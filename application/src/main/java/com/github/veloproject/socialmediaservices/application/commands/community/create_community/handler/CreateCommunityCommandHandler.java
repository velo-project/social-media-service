package com.github.veloproject.socialmediaservices.application.commands.community.create_community.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class CreateCommunityCommandHandler extends AuthRequestHandler<CreateCommunityCommand, CreateCommunityCommandResult> {
    private final ICommunityRepository communityRepository;
    private final IUserGRPCClient userServices;
    private final ICommunityMemberRepository communityMemberRepository;

    public CreateCommunityCommandHandler(ICommunityRepository communityRepository,
                                         IUserGRPCClient userServices,
                                         ICommunityMemberRepository communityMemberRepository) {
        this.communityRepository = communityRepository;
        this.userServices = userServices;
        this.communityMemberRepository = communityMemberRepository;
    }

    @Override
    @Transactional
    public CreateCommunityCommandResult handle(CreateCommunityCommand request, JwtAuthenticationToken token) {
        var integerSubject = Integer.valueOf(token.getToken().getSubject());
        var userExists = userServices.existsByUserId(integerSubject);
        if (!userExists)
            throw new InvalidUserProvidedException();

        var community = CommunityEntity.builder()
                .name(request.name())
                .description(request.description())
                .createdBy(integerSubject)
                .build();

        var communityId = communityRepository.save(community);
        communityMemberRepository.save(CommunityMemberEntity.builder()
                .communityId(communityId)
                .userId(integerSubject)
                .build());

        return new CreateCommunityCommandResult(200, communityId);
    }
}

package com.github.veloproject.socialmediaservices.application.commands.post.publish_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
import com.github.veloproject.socialmediaservices.application.commands.post.publish_post.PublishPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.post.publish_post.PublishPostCommandResult;
import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidCommunityProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserNotInCommunityException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublishPostCommandHandler extends AuthRequestHandler<PublishPostCommand, PublishPostCommandResult> {
    private final IPostRepository postRepository;
    private final ICommunityRepository communityRepository;
    private final IUserServices userServices;
    private final ICommunityMemberRepository communityMemberRepository;

    public PublishPostCommandHandler(
            IPostRepository postRepository,
            ICommunityRepository communityRepository,
            IUserServices userServices,
            ICommunityMemberRepository communityMemberRepository
    ) {
        this.postRepository = postRepository;
        this.communityRepository = communityRepository;
        this.userServices = userServices;
        this.communityMemberRepository = communityMemberRepository;
    }

    @Transactional
    @Override
    public PublishPostCommandResult handle(PublishPostCommand request,
                                           JwtAuthenticationToken token) {
        var user = getUserByToken(token);
        var community = getCommunityByIdOrReturnNull(request.postedIn());

        if (!communityMemberRepository.existsMember(community.getId(), user.id())) throw new UserNotInCommunityException();

        var post = PostEntity.builder()
                .content(request.content())
                .postedBy(user.id())
                .postedIn(community)
                .build();

        var postId = postRepository.save(post);

        return new PublishPostCommandResult(
                200,
                postId
        );
    }

    private UserInfo getUserByToken(JwtAuthenticationToken token) throws InvalidUserProvidedException {
        var integerSubject = Integer.valueOf(
                token.getToken()
                .getSubject());

        var user = userServices
                .getUserById(integerSubject);

        return Optional.ofNullable(user)
                .orElseThrow(InvalidUserProvidedException::new);
    }

    private CommunityEntity getCommunityByIdOrReturnNull(Integer communityId) {
        return (communityId != null) ? communityRepository
                .findById(communityId)
                .orElseThrow(InvalidCommunityProvidedException::new) : null;
    }
}
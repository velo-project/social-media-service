package com.github.veloproject.socialmediaservices.application.commands.publish_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
import com.github.veloproject.socialmediaservices.application.commands.publish_post.PublishPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.publish_post.PublishPostCommandResult;
import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidCommunityProvided;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class PublishPostCommandHandler extends AuthRequestHandler<PublishPostCommand, PublishPostCommandResult> {
    private final IPostRepository postRepository;
    private final ICommunityRepository communityRepository;
    private final IUserServices userServices;

    public PublishPostCommandHandler(
            IPostRepository postRepository,
            ICommunityRepository communityRepository,
            IUserServices userServices
    ) {
        this.postRepository = postRepository;
        this.communityRepository = communityRepository;
        this.userServices = userServices;
    }

    // TODO Validação de usuário por annotation
    // TODO Sistema de Hashtags
    // TODO Exception Handlers
    @Override
    public PublishPostCommandResult handle(PublishPostCommand request,
                                           JwtAuthenticationToken token) {
        var user = getUserByToken(token);
        var community = getCommunityById(request.postedIn());

        System.out.println(user.name());

        var post = PostEntity.builder()
                .content(request.content())
                .postedBy(user.id())
                .postedIn(community)
                .build();

        var postId = postRepository.save(post);

        return new PublishPostCommandResult(
                200,
                "Post publicado.",
                postId
        );
    }

    private UserInfo getUserByToken(JwtAuthenticationToken token) throws InvalidUserProvidedException {
        var integerSubject = Integer.valueOf(
                token.getToken()
                .getSubject());

        return userServices
                .getUserById(integerSubject);
    }

    private CommunityEntity getCommunityById(Integer communityId) {
        if (communityId == null) return null;

        return communityRepository
                .findById(communityId)
                .orElseThrow(InvalidCommunityProvided::new);
    }
}
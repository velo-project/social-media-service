package com.github.veloproject.socialmediaservices.application.commands.follower.follow_user.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.commands.follower.follow_user.FollowUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.follower.follow_user.FollowUserCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class FollowUserCommandHandler extends AuthRequestHandler<FollowUserCommand, FollowUserCommandResult> {
    private final IUserFollowerRepository followerRepository;
    private final IUserGRPCClient userServices;

    public FollowUserCommandHandler(IUserFollowerRepository followerRepository,
                                    IUserGRPCClient userServices) {
        this.followerRepository = followerRepository;
        this.userServices = userServices;
    }

    @Transactional
    @Override
    public FollowUserCommandResult handle(FollowUserCommand request,
                                          JwtAuthenticationToken token) {
        var tokenObject = token.getToken();
        var subject = Integer.valueOf(tokenObject.getSubject());
        var userNickname = tokenObject.getClaim("nickname");

        var following = userServices.getUserByNickname(request.nickname());
        if (!userServices.existsByUserId(subject) || following == null)
            throw new InvalidUserProvidedException();
        else if (userNickname.equals(request.nickname()))
            throw new InvalidUserProvidedException();

        var entity = UserFollowerEntity.builder()
                .userId(following.id()) // Usuário a ser seguido
                .followerId(subject) // Seguidor
                .build();
        followerRepository.save(entity);

        return new FollowUserCommandResult(200);
    }
}

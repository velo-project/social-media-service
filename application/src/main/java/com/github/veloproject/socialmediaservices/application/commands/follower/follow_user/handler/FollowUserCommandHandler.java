package com.github.veloproject.socialmediaservices.application.commands.follower.follow_user.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
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
    private final IUserServices userServices;

    public FollowUserCommandHandler(IUserFollowerRepository followerRepository,
                                    IUserServices userServices) {
        this.followerRepository = followerRepository;
        this.userServices = userServices;
    }

    @Transactional
    @Override
    public FollowUserCommandResult handle(FollowUserCommand request,
                                          JwtAuthenticationToken token) {
        var subject = Integer.valueOf(token.getToken().getSubject());

        if (!userServices.existsById(subject) || !userServices.existsById(request.userId()))
            throw new InvalidUserProvidedException();

        var entity = UserFollowerEntity.builder()
                .userId(request.userId())
                .followerId(subject)
                .build();
        followerRepository.save(entity);

        return new FollowUserCommandResult(200);
    }
}

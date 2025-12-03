package com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UnfollowUserCommandHandler extends AuthRequestHandler<UnfollowUserCommand, UnfollowUserCommandResult> {
    private final IUserFollowerRepository userFollowerRepository;
    private final IUserGRPCClient userGRPCClient;

    public UnfollowUserCommandHandler(IUserFollowerRepository userFollowerRepository,
                                      IUserGRPCClient userGRPCClient) {
        this.userFollowerRepository = userFollowerRepository;
        this.userGRPCClient = userGRPCClient;
    }

    @Transactional
    @Override
    public UnfollowUserCommandResult handle(UnfollowUserCommand request,
                                            JwtAuthenticationToken token) {
        var subject = Integer.valueOf(token.getToken().getSubject());
        var following = Optional.ofNullable(userGRPCClient.getUserByNickname(request.nickname()))
                .orElseThrow(InvalidUserProvidedException::new);
        if (Objects.equals(following.id(), subject)) throw new InvalidUserProvidedException();
        userFollowerRepository.delete(following.id(), subject);

        return new UnfollowUserCommandResult(200);
    }
}

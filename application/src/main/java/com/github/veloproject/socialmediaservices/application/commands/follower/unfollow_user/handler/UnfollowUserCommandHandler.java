package com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UnfollowUserCommandHandler extends AuthRequestHandler<UnfollowUserCommand, UnfollowUserCommandResult> {
    private final IUserFollowerRepository userFollowerRepository;

    public UnfollowUserCommandHandler(IUserFollowerRepository userFollowerRepository) {
        this.userFollowerRepository = userFollowerRepository;
    }

    @Override
    public UnfollowUserCommandResult handle(UnfollowUserCommand request,
                                            JwtAuthenticationToken token) {
        var subject = Integer.valueOf(token.getToken().getSubject());
        userFollowerRepository.delete(request.userId(), subject);

        return new UnfollowUserCommandResult(200);
    }
}

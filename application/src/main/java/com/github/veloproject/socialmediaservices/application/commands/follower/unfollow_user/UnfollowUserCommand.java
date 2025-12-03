package com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record UnfollowUserCommand(
        String nickname
) implements Request<UnfollowUserCommandResult> {
}

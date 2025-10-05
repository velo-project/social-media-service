package com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UnfollowUserCommand(
        @NotNull @Positive Integer userId
) implements Request<UnfollowUserCommandResult> {
}

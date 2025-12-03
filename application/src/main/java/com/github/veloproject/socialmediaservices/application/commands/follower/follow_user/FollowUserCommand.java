package com.github.veloproject.socialmediaservices.application.commands.follower.follow_user;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FollowUserCommand(
        @NotBlank String nickname
        ) implements Request<FollowUserCommandResult> {
}

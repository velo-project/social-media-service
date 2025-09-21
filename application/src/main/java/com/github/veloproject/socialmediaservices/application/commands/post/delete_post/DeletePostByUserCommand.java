package com.github.veloproject.socialmediaservices.application.commands.post.delete_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.Positive;

public record DeletePostByUserCommand(
        @Positive Integer postId
        ) implements Request<DeletePostByUserCommandResult> {
}

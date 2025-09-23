package com.github.veloproject.socialmediaservices.application.commands.comment.publish_comment_on_a_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PublishCommentOnAPostCommand(
        @NotNull @Positive Integer postId,
        @NotBlank String content
)
        implements Request<PublishCommentOnAPostCommandResult> {
}

package com.github.veloproject.socialmediaservices.application.commands.publish_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublishPostCommand(
        @NotBlank @Size( max = 355 ) String content,
        Integer postedIn
) implements Request<PublishPostCommandResult> {
}

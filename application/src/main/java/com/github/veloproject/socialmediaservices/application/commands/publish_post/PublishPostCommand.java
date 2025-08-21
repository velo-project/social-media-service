package com.github.veloproject.socialmediaservices.application.commands.publish_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record PublishPostCommand(
        @NotBlank @Max(355) String content,
        CommunityEntity postedIn
) implements Request<PublishPostCommandResult> {
}

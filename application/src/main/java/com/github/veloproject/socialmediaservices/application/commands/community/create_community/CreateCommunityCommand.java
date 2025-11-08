package com.github.veloproject.socialmediaservices.application.commands.community.create_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommunityCommand(
        @NotBlank @Size(min = 3, max = 25) String name,
        @NotBlank @Size(min = 1, max = 255) String description
) implements Request<CreateCommunityCommandResult> {
}

package com.github.veloproject.socialmediaservices.application.commands.community_member.join_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record JoinCommunityCommand(
        @NotNull @Positive Integer communityId
) implements Request<JoinCommunityCommandResult> {
}

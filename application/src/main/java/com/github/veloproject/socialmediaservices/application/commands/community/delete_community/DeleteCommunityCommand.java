package com.github.veloproject.socialmediaservices.application.commands.community.delete_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record DeleteCommunityCommand(
        Integer id
) implements Request<DeleteCommunityCommandResult> {
}

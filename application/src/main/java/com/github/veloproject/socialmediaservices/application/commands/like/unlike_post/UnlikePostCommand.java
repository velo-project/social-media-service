package com.github.veloproject.socialmediaservices.application.commands.like.unlike_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record UnlikePostCommand(Integer postId) implements Request<UnlikePostCommandResult> {
}

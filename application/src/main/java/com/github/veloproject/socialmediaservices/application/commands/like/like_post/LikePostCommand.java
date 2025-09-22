package com.github.veloproject.socialmediaservices.application.commands.like.like_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record LikePostCommand(Integer postId) implements Request<LikePostCommandResult> {
}

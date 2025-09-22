package com.github.veloproject.socialmediaservices.application.commands.like.like_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class LikePostCommandResult extends Response {
    public LikePostCommandResult(Integer statusCode) {
        super(statusCode);
    }
}

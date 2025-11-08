package com.github.veloproject.socialmediaservices.application.commands.like.unlike_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class UnlikePostCommandResult extends Response {
    public UnlikePostCommandResult(Integer statusCode) {
        super(statusCode);
    }
}

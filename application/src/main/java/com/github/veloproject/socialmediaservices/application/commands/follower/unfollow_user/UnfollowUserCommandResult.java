package com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class UnfollowUserCommandResult extends Response {
    public UnfollowUserCommandResult(Integer statusCode) {
        super(statusCode);
    }
}

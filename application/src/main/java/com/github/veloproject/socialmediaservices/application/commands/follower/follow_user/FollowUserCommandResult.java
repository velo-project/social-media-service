package com.github.veloproject.socialmediaservices.application.commands.follower.follow_user;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class FollowUserCommandResult extends Response {
    public FollowUserCommandResult(Integer statusCode) {
        super(statusCode);
    }
}

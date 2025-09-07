package com.github.veloproject.socialmediaservices.application.commands.publish_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class PublishPostCommandResult extends Response {
    private final Integer postId;

    public PublishPostCommandResult(Integer statusCode, String message, Integer postId) {
        super(statusCode, message);
        this.postId = postId;
    }
}

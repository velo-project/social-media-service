package com.github.veloproject.socialmediaservices.application.commands.publish_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class PublishPostCommandResult extends Response {
    public PublishPostCommandResult(Integer statusCode, String message) {
        super(statusCode, message);
    }
}

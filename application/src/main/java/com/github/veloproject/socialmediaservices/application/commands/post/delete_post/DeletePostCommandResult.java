package com.github.veloproject.socialmediaservices.application.commands.post.delete_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class DeletePostCommandResult extends Response {
    public DeletePostCommandResult(Integer statusCode, String message) {
        super(statusCode, message);
    }
}

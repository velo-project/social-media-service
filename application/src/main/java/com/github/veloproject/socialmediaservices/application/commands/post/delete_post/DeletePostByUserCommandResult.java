package com.github.veloproject.socialmediaservices.application.commands.post.delete_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class DeletePostByUserCommandResult extends Response {
    public DeletePostByUserCommandResult(Integer statusCode) {
        super(statusCode);
    }
}

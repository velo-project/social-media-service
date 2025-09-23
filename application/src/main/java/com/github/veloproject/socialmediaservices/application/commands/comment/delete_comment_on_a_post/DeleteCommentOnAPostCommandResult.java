package com.github.veloproject.socialmediaservices.application.commands.comment.delete_comment_on_a_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class DeleteCommentOnAPostCommandResult extends Response {
    public DeleteCommentOnAPostCommandResult(Integer statusCode) {
        super(statusCode);
    }
}

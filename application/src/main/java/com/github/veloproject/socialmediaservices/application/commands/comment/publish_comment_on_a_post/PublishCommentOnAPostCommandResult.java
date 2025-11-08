package com.github.veloproject.socialmediaservices.application.commands.comment.publish_comment_on_a_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import lombok.Getter;

@Getter
public class PublishCommentOnAPostCommandResult extends Response {
    private final Integer commentId;

    public PublishCommentOnAPostCommandResult(Integer statusCode, Integer commentId) {
        super(statusCode);
        this.commentId = commentId;
    }
}

package com.github.veloproject.socialmediaservices.application.commands.post.publish_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import lombok.Getter;

@Getter
public class PublishPostCommandResult extends Response {
    private final Integer postId;

    public PublishPostCommandResult(Integer statusCode, Integer postId) {
        super(statusCode);
        this.postId = postId;
    }
}

package com.github.veloproject.socialmediaservices.presentation.controllers.comment;

import com.github.veloproject.socialmediaservices.application.commands.comment.publish_comment_on_a_post.PublishCommentOnAPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.comment.publish_comment_on_a_post.PublishCommentOnAPostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/comments")
public class PublishCommentOnAPostController {
    private final LoggingMediatorImp mediator;

    public PublishCommentOnAPostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/v1/publish")
    public ResponseEntity<PublishCommentOnAPostCommandResult> publishComment(
            @RequestBody @Valid PublishCommentOnAPostCommand command,
            JwtAuthenticationToken token
    ) {
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

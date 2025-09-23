package com.github.veloproject.socialmediaservices.presentation.controllers.comment;

import com.github.veloproject.socialmediaservices.application.commands.comment.delete_comment_on_a_post.DeleteCommentOnAPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.comment.delete_comment_on_a_post.DeleteCommentOnAPostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/comments")
public class DeleteCommentOnAPostController {
    private final LoggingMediatorImp mediator;

    public DeleteCommentOnAPostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/v1/delete")
    public ResponseEntity<DeleteCommentOnAPostCommandResult> deleteComment(
            @RequestParam("id") @Valid @Positive Integer id,
            JwtAuthenticationToken token
    ) {
        var command =  new DeleteCommentOnAPostCommand(id);
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

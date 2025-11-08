package com.github.veloproject.socialmediaservices.presentation.controllers.like;

import com.github.veloproject.socialmediaservices.application.commands.like.unlike_post.UnlikePostCommand;
import com.github.veloproject.socialmediaservices.application.commands.like.unlike_post.UnlikePostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/likes")
public class UnlikePostController {
    private final LoggingMediatorImp mediator;

    public UnlikePostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/v1/unlike")
    public ResponseEntity<UnlikePostCommandResult> unlikePost(
            @RequestParam @Valid Integer postId,
            JwtAuthenticationToken token
    ) {
        var command = new UnlikePostCommand(postId);
        var response = mediator.send(command, token);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

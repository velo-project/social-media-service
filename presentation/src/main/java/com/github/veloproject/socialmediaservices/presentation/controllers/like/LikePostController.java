package com.github.veloproject.socialmediaservices.presentation.controllers.like;

import com.github.veloproject.socialmediaservices.application.commands.like.like_post.LikePostCommand;
import com.github.veloproject.socialmediaservices.application.commands.like.like_post.LikePostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/likes")
public class LikePostController {
    private final LoggingMediatorImp mediator;

    public LikePostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/v1/like")
    public ResponseEntity<LikePostCommandResult> likePost(
            @RequestBody @Valid LikePostCommand command,
            JwtAuthenticationToken token
    ) {
        var response = mediator.send(command, token);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

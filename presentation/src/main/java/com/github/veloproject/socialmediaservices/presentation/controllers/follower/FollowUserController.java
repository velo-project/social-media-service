package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.commands.follower.follow_user.FollowUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.follower.follow_user.FollowUserCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/followers")
public class FollowUserController {
    private final LoggingMediatorImp mediator;

    public FollowUserController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/v1/follow")
    public ResponseEntity<FollowUserCommandResult> followUser(
            @RequestBody @Valid FollowUserCommand command,
            JwtAuthenticationToken token
    ) {
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

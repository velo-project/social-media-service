package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/followers")
public class UnfollowUserController {
    private final LoggingMediatorImp mediator;

    public UnfollowUserController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/v1/unfollow")
    public ResponseEntity<UnfollowUserCommandResult> unfollowUser(
            @RequestParam @Valid @NotBlank String nickname,
            JwtAuthenticationToken token
    ) {
        var command = new UnfollowUserCommand(nickname);
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

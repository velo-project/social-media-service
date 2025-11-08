package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.follower.unfollow_user.UnfollowUserCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
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
            @RequestParam Integer userId,
            JwtAuthenticationToken token
    ) {
        var command = new UnfollowUserCommand(userId);
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

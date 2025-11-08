package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.commands.post.delete_post.DeletePostByUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.post.delete_post.DeletePostByUserCommandResult;
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
@RequestMapping("/api/social_media/posts")
public class DeletePostByUserController {
    private final LoggingMediatorImp mediator;

    public DeletePostByUserController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/v1/delete")
    public ResponseEntity<DeletePostByUserCommandResult> deletePost(
            @RequestParam("id") @Valid @Positive Integer id,
            JwtAuthenticationToken token
    ) {
        var command =  new DeletePostByUserCommand(id);
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

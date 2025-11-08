package com.github.veloproject.socialmediaservices.presentation.controllers.community;

import com.github.veloproject.socialmediaservices.application.commands.community.delete_community.DeleteCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community.delete_community.DeleteCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/communities")
public class DeleteCommunityController {
    private final LoggingMediatorImp mediator;

    public DeleteCommunityController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/v1/delete")
    public ResponseEntity<DeleteCommunityCommandResult> deleteCommunity(
        @RequestParam @Valid @NotNull @Positive Integer id,
        JwtAuthenticationToken token
    ) {
        var command = new DeleteCommunityCommand(id);
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

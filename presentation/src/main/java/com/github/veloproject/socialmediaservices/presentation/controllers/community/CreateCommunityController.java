package com.github.veloproject.socialmediaservices.presentation.controllers.community;

import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/communities")
public class CreateCommunityController {
    private final LoggingMediatorImp mediator;

    public CreateCommunityController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    // TODO Imagens (Banner & Foto)
    @PostMapping("/v1/create")
    public ResponseEntity<CreateCommunityCommandResult> createCommunity(
            @RequestBody @Valid CreateCommunityCommand command,
            JwtAuthenticationToken token
    ) {
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.commands.publish_post.PublishPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.publish_post.PublishPostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media_services/post")
public class PublishPostController {
    private final LoggingMediatorImp mediator;

    public PublishPostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    // TODO Configurar Spring Security
    @PostMapping
    public ResponseEntity<PublishPostCommandResult> publishPost(
            @RequestBody @Valid PublishPostCommand command,
            JwtAuthenticationToken token) {
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

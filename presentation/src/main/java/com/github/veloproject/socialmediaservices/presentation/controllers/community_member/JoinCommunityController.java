package com.github.veloproject.socialmediaservices.presentation.controllers.community_member;

import com.github.veloproject.socialmediaservices.application.commands.community_member.join_community.JoinCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community_member.join_community.JoinCommunityCommandResult;
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
public class JoinCommunityController {
    private final LoggingMediatorImp mediator;

    public JoinCommunityController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/v1/join")
    public ResponseEntity<JoinCommunityCommandResult> joinCommunity(
            @RequestBody @Valid JoinCommunityCommand command,
            JwtAuthenticationToken token
    ) {
        var response = mediator.send(command, token);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

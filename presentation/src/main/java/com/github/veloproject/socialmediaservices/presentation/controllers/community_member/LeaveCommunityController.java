package com.github.veloproject.socialmediaservices.presentation.controllers.community_member;

import com.github.veloproject.socialmediaservices.application.commands.community_member.leave_community.LeaveCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community_member.leave_community.LeaveCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/communities")
public class LeaveCommunityController {
    private final LoggingMediatorImp mediator;

    public LeaveCommunityController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/v1/leave")
    public ResponseEntity<LeaveCommunityCommandResult> leaveCommunity(
            @RequestParam @Valid Integer communityId,
            JwtAuthenticationToken token
    ) {
        var command = new LeaveCommunityCommand(communityId);
        var response = mediator.send(command, token);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

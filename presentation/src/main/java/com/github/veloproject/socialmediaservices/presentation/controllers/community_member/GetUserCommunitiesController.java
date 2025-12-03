package com.github.veloproject.socialmediaservices.presentation.controllers.community_member;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_user_communities.GetUserCommunitiesQuery;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_user_communities.GetUserCommunitiesQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/communities")
public class GetUserCommunitiesController {
    private final LoggingMediatorImp mediator;

    public GetUserCommunitiesController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/user")
    public ResponseEntity<GetUserCommunitiesQueryResult> getMembersByCommunityId(
            @PathParam("nickname") @Valid @NotBlank String nickname
    ) {
        var query = new GetUserCommunitiesQuery(nickname);
        var response = mediator.send(query);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

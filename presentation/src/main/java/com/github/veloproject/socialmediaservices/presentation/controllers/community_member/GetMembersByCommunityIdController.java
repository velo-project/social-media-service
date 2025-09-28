package com.github.veloproject.socialmediaservices.presentation.controllers.community_member;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.GetMembersByCommunityIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.community_member.get_members_by_community_id.GetMembersByCommunityIdQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/communities")
public class GetMembersByCommunityIdController {
    private final LoggingMediatorImp mediator;

    public GetMembersByCommunityIdController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/members/{communityId}")
    public ResponseEntity<GetMembersByCommunityIdQueryResult> getMembersByCommunityId(
            @PathVariable @Valid @Positive Integer communityId,
            @RequestParam @Valid @Positive Integer page
    ) {
        var query = new GetMembersByCommunityIdQuery(communityId, page);
        var response = mediator.send(query);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

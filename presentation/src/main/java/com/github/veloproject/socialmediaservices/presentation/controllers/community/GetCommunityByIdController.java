package com.github.veloproject.socialmediaservices.presentation.controllers.community;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.community.get_community_by_id.GetCommunityByIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.community.get_community_by_id.GetCommunityByIdQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/communities")
public class GetCommunityByIdController {
    private final LoggingMediatorImp mediator;

    public GetCommunityByIdController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/community")
    public ResponseEntity<GetCommunityByIdQueryResult> getCommunityById(
            @RequestParam @Valid @Positive Integer id
    ) {
        var query = new GetCommunityByIdQuery(id);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

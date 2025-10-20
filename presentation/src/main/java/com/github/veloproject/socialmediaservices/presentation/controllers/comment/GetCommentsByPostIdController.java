package com.github.veloproject.socialmediaservices.presentation.controllers.comment;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.comment.GetCommentsByPostIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.comment.GetCommentsByPostIdQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/comments")
public class GetCommentsByPostIdController {
    private final LoggingMediatorImp mediator;

    public GetCommentsByPostIdController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/{postId}")
    public ResponseEntity<GetCommentsByPostIdQueryResult> getCommunityById(
            @PathVariable @Valid @Positive Integer postId,
            @RequestParam @Valid @PositiveOrZero Integer page
    ) {
        var query = new GetCommentsByPostIdQuery(postId, page);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

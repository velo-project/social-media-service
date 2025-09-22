package com.github.veloproject.socialmediaservices.presentation.controllers.like;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post.GetUsersLikedAPostQuery;
import com.github.veloproject.socialmediaservices.application.queries.like.get_users_liked_a_post.GetUsersLikedAPostQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/likes")
public class GetUsersLikedAPostController {
    private final LoggingMediatorImp mediator;

    public GetUsersLikedAPostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/{postId}")
    public ResponseEntity<GetUsersLikedAPostQueryResult> getUsersLikedAPost(
            @PathVariable @Valid @Positive Integer postId,
            @RequestParam @Valid @Positive Integer page
            ) {
        var query = new GetUsersLikedAPostQuery(postId, page);
        var response = mediator.send(query);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

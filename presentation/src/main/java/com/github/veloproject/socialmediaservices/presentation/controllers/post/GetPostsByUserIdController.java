package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.post.get_posts_by_user_id.GetPostsByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.post.get_posts_by_user_id.GetPostsByUserIdQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/posts")
public class GetPostsByUserIdController {
    private final LoggingMediatorImp mediator;

    public GetPostsByUserIdController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/user/{userId}")
    public ResponseEntity<GetPostsByUserIdQueryResult> getPostsByUserId(
            @PathVariable @Valid @Positive Integer userId,
            @RequestParam @Valid @PositiveOrZero Integer page
            ) {
        var query = new GetPostsByUserIdQuery(userId, page);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

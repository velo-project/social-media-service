package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.GetFollowersByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.GetFollowersByUserIdQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/followers")
public class GetFollowersByUserIdController {
    private final LoggingMediatorImp mediator;

    public GetFollowersByUserIdController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/{userId}")
    public ResponseEntity<GetFollowersByUserIdQueryResult> getFollowers(@PathVariable @Valid @Positive Integer userId,
                                                                        @RequestParam @Valid @Positive Integer page) {
        var query = new GetFollowersByUserIdQuery(userId, page);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

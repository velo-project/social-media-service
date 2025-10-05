package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.GetFollowingByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.GetFollowingByUserIdQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/following")
public class GetFollowingByUserIdController {
    private final LoggingMediatorImp mediator;

    public GetFollowingByUserIdController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/{userId}")
    public ResponseEntity<GetFollowingByUserIdQueryResult> getFollowing(@PathVariable @Valid @Positive Integer userId,
                                                                        @RequestParam @Valid @Positive Integer page) {
        var query = new GetFollowingByUserIdQuery(userId, page);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}
package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_nickname.GetFollowingByNicknameQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_nickname.GetFollowingByNicknameQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/following")
public class GetFollowingByNicknameController {
    private final LoggingMediatorImp mediator;

    public GetFollowingByNicknameController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/{userId}")
    public ResponseEntity<GetFollowingByNicknameQueryResult> getFollowing(@RequestParam @Valid @NotBlank String nickname,
                                                                          @RequestParam @Valid @PositiveOrZero Integer page) {
        var query = new GetFollowingByNicknameQuery(nickname, page);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}
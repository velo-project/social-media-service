package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_nickname.GetFollowersByNicknameQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_nickname.GetFollowersByNicknameQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/followers")
public class GetFollowersByNicknameController {
    private final LoggingMediatorImp mediator;

    public GetFollowersByNicknameController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/user")
    public ResponseEntity<GetFollowersByNicknameQueryResult> getFollowers(@RequestParam @Valid @NotBlank String nickname,
                                                                          @RequestParam @Valid @PositiveOrZero Integer page) {
        var query = new GetFollowersByNicknameQuery(nickname, page);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

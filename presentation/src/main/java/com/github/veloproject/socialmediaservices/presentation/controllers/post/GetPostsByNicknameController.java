package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_posts_by_nickname.GetPostsByNicknameQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_posts_by_nickname.GetPostsByNicknameQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/posts")
public class GetPostsByNicknameController {
    private final LoggingMediatorImp mediator;

    public GetPostsByNicknameController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/user")
    public ResponseEntity<GetPostsByNicknameQueryResult> getPostsByNickname(
            @RequestParam @Valid @NotBlank String nickname,
            @RequestParam @Valid @PositiveOrZero Integer page
    ) {
        var query = new GetPostsByNicknameQuery(nickname, page);
        var response = mediator.send(query);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

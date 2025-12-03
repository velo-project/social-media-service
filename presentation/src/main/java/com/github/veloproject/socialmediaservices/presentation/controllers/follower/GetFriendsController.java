package com.github.veloproject.socialmediaservices.presentation.controllers.follower;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_friends.GetFriendsQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_friends.GetFriendsQueryResult;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/friends")
public class GetFriendsController {
    private final LoggingMediatorImp mediator;

    public GetFriendsController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/list")
    public ResponseEntity<GetFriendsQueryResult> getFriends(JwtAuthenticationToken token) {
        var query = new GetFriendsQuery();
        var response = mediator.send(query, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

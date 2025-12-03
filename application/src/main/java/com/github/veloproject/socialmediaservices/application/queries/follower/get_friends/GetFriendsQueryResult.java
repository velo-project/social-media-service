package com.github.veloproject.socialmediaservices.application.queries.follower.get_friends;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetFriendsQueryResult extends Response {
    private final List<String> friends;

    public GetFriendsQueryResult(Integer statusCode, String message, List<String> friends) {
        super(statusCode, message);
        this.friends = friends;
    }
}

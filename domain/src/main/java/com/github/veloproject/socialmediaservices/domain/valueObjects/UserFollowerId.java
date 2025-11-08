package com.github.veloproject.socialmediaservices.domain.valueObjects;

import java.io.Serializable;

public record UserFollowerId(
        Integer userId,
        Integer followerId
) implements Serializable {
}

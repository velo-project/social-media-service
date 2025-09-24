package com.github.veloproject.socialmediaservices.domain.valueObjects;

import java.io.Serializable;

public record CommunityMemberId(
        Integer communityId,
        Integer userId
) implements Serializable {
}

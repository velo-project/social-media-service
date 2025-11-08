package com.github.veloproject.socialmediaservices.domain.valueObjects;

import java.io.Serializable;

public record PostLikeId(Integer postId, Integer likedBy) implements Serializable {
}

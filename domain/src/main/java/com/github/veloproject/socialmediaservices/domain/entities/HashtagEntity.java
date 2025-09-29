package com.github.veloproject.socialmediaservices.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class HashtagEntity {
    private Integer id;
    private String tag;
}

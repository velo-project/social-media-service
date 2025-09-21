package com.github.veloproject.socialmediaservices.presentation.controllers.like;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/posts")
public class LikePostController {
    private final LoggingMediatorImp mediator;

    public LikePostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    public
}

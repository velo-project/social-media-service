package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media_services/post/")
public class PublishPostController {
    private final LoggingMediatorImp mediator;

    public PublishPostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }
}

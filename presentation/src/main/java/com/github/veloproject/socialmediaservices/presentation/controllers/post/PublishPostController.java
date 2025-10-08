package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.commands.post.publish_post.PublishPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.post.publish_post.PublishPostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/social_media/posts")
public class PublishPostController {
    private final LoggingMediatorImp mediator;

    public PublishPostController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/v1/publish")
    public ResponseEntity<PublishPostCommandResult> publishPost(
            @Valid PublishPostForm postForm,
            @RequestParam("image") MultipartFile image,
            JwtAuthenticationToken token
    ) {
        var command =  new PublishPostCommand(postForm.content, postForm.postedIn, image);
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }

    public record PublishPostForm(
            @NotBlank @Size( max = 355 ) String content,
            Integer postedIn) {}
}

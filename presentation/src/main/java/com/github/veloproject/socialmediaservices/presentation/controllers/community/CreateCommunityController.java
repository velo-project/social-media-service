package com.github.veloproject.socialmediaservices.presentation.controllers.community;

import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/social_media/communities")
public class CreateCommunityController {
    private final LoggingMediatorImp mediator;

    public CreateCommunityController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/v1/create")
    public ResponseEntity<CreateCommunityCommandResult> createCommunity(
            @RequestParam(required = true) @NotBlank @Size(min = 3, max = 25) String name,
            @RequestParam(required = true) @NotBlank @Size(min = 1, max = 255) String description,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "banner", required = false) MultipartFile banner,
            JwtAuthenticationToken token
    ) {
        var command = new CreateCommunityCommand(name, description, photo, banner);
        var response = mediator.send(command, token);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}

package com.github.veloproject.socialmediaservices.application.commands.community.create_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record CreateCommunityCommand(
        String name,
        String description,
        MultipartFile photo,
        MultipartFile banner
) implements Request<CreateCommunityCommandResult> {
}

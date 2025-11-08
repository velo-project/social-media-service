package com.github.veloproject.socialmediaservices.application.commands.post.publish_post;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record PublishPostCommand(
        String content,
        Integer postedIn,
        MultipartFile optionalImage
) implements Request<PublishPostCommandResult> {
}

package com.github.veloproject.socialmediaservices.application.abstractions;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageFileService {
    String uploadImage(MultipartFile file, Integer userId) throws IOException;
}

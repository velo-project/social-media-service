package com.github.veloproject.socialmediaservices.application.commands.community.create_community.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.*;
import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community.create_community.CreateCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityMemberEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InternalErrorException;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.function.Consumer;

@Service
public class CreateCommunityCommandHandler extends AuthRequestHandler<CreateCommunityCommand, CreateCommunityCommandResult> {
    private final ICommunityRepository communityRepository;
    private final IUserGRPCClient userServices;
    private final ICommunityMemberRepository communityMemberRepository;
    private final IGeminiAPIService geminiAPIService;
    private final IImageFileService imageService;

    public CreateCommunityCommandHandler(ICommunityRepository communityRepository,
                                         IUserGRPCClient userServices,
                                         ICommunityMemberRepository communityMemberRepository,
                                         IGeminiAPIService geminiAPIService,
                                         IImageFileService imageService) {
        this.communityRepository = communityRepository;
        this.userServices = userServices;
        this.communityMemberRepository = communityMemberRepository;
        this.geminiAPIService = geminiAPIService;
        this.imageService = imageService;
    }

    @Override
    @Transactional
    public CreateCommunityCommandResult handle(CreateCommunityCommand request, JwtAuthenticationToken token) {
        var integerSubject = Integer.valueOf(token.getToken().getSubject());
        var userExists = userServices.existsByUserId(integerSubject);
        if (!userExists)
            throw new InvalidUserProvidedException();

        var embeddings = geminiAPIService
                .generateEmbeddings(request.name()
                        + " "
                        + request.description());

        var community = CommunityEntity.builder()
                .name(request.name())
                .description(request.description())
                .embeddings(embeddings)
                .createdBy(integerSubject)
                .build();

        processImageUpload(request.photo(), community::setPhotoUrl, integerSubject);
        processImageUpload(request.banner(), community::setBannerUrl, integerSubject);

        var communityId = communityRepository.save(community);
        communityMemberRepository.save(CommunityMemberEntity.builder()
                .communityId(communityId)
                .userId(integerSubject)
                .build());

        return new CreateCommunityCommandResult(200, communityId);
    }

    private void processImageUpload(MultipartFile file, Consumer<String> urlSetter, Integer userId) {
        if (file != null) {
            try {
                String path = imageService.uploadImage(file, userId);
                urlSetter.accept(path);
            } catch (IOException e) {
                throw new InternalErrorException("Erro ao processar imagem.");
            }
        }
    }
}

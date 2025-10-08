package com.github.veloproject.socialmediaservices.application.commands.post.publish_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.*;
import com.github.veloproject.socialmediaservices.application.commands.post.publish_post.PublishPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.post.publish_post.PublishPostCommandResult;
import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;
import com.github.veloproject.socialmediaservices.domain.entities.HashtagEntity;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidCommunityProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserNotInCommunityException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PublishPostCommandHandler extends AuthRequestHandler<PublishPostCommand, PublishPostCommandResult> {
    private final IPostRepository postRepository;
    private final ICommunityRepository communityRepository;
    private final IUserGRPCClient userServices;
    private final ICommunityMemberRepository communityMemberRepository;
    private final IHashtagRepository hashtagRepository;

    public PublishPostCommandHandler(
            IPostRepository postRepository,
            ICommunityRepository communityRepository,
            IUserGRPCClient userServices,
            ICommunityMemberRepository communityMemberRepository,
            IHashtagRepository hashtagRepository
    ) {
        this.postRepository = postRepository;
        this.communityRepository = communityRepository;
        this.userServices = userServices;
        this.communityMemberRepository = communityMemberRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @Transactional
    @Override
    public PublishPostCommandResult handle(PublishPostCommand request,
                                           JwtAuthenticationToken token) {
        var user = getUserByToken(token);
        var community = getCommunityByIdOrReturnNull(request.postedIn());

        if (community != null)
            if (!communityMemberRepository.existsMember(community.getId(), user.id())) throw new UserNotInCommunityException();

        var hashtags = extractHashtagsFromContent(request.content());

        var post = PostEntity.builder()
                .content(request.content())
                .postedBy(user.id())
                .postedIn(community)
                .hashtags(hashtags)
                .build();

        var savedPost = postRepository.save(post);

        return new PublishPostCommandResult(
                200,
                savedPost.getId()
        );
    }

    private UserInfo getUserByToken(JwtAuthenticationToken token) throws InvalidUserProvidedException {
        var integerSubject = Integer.valueOf(
                token.getToken()
                .getSubject());

        var user = userServices
                .getUserById(integerSubject);

        return Optional.ofNullable(user)
                .orElseThrow(InvalidUserProvidedException::new);
    }

    private CommunityEntity getCommunityByIdOrReturnNull(Integer communityId) {
        return (communityId != null) ? communityRepository
                .findById(communityId)
                .orElseThrow(InvalidCommunityProvidedException::new) : null;
    }

    private Set<HashtagEntity> extractHashtagsFromContent(String context) {
        ArrayList<HashtagEntity> extractedHashtags = new ArrayList<>();

        String regex = "#(\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(context);

        while (matcher.find()) {
            String tag = matcher.group(1);

            var optionalHashtag =  hashtagRepository.findByTag(tag);
            HashtagEntity hashtag;

            hashtag = optionalHashtag.orElseGet(() -> hashtagRepository.save(
                    HashtagEntity.builder()
                            .tag(tag)
                            .build()));

            extractedHashtags.add(hashtag);
        }

        return Set.copyOf(extractedHashtags);
    }
}
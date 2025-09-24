package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityMemberRepository;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.ICommunityRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityMemberRepositoryImp implements ICommunityMemberRepository {
    private final ICommunityRepositoryJpa jpa;

    public CommunityMemberRepositoryImp(ICommunityRepositoryJpa jpa) {
        this.jpa = jpa;
    }
}

package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;

public interface IUserGRPCClient {
    boolean existsByUserId(Integer userId);
    UserInfo getUserById(Integer userId);
}

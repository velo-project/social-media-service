package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;

import java.util.List;

public interface IUserGRPCClient {
    boolean existsByUserId(Integer userId);
    UserInfo getUserById(Integer userId);
    List<UserInfo> getUsersByIdList(List<Integer> ids);
}

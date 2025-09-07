package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;

public interface IUserServices {
    UserInfo getUserById(Integer id);
}

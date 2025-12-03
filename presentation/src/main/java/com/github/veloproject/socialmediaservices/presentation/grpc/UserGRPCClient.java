package com.github.veloproject.socialmediaservices.presentation.grpc;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserGRPCClient implements IUserGRPCClient {
    @GrpcClient("user-services")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @Override
    public boolean existsByUserId(Integer userId) {
        try {
            UserExistsByIdRequest request = UserExistsByIdRequest.newBuilder().setId(userId).build();
            UserExistsByIdResponse response = userServiceStub.userExistsById(request);
            return response.getExists();
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                return false;
            }
            else {
                throw e;
            }
        }
    }

    @Override
    public UserInfo getUserByNickname(String nickname) {
        try {
            GetUserByNicknameRequest request = GetUserByNicknameRequest.newBuilder().setNickname(nickname).build();
            GetUserByNicknameResponse response = userServiceStub.getUserByNickname(request);
            var user = response.getUser();

            return new UserInfo(
                    user.getId(),
                    user.getName(),
                    user.getNickname(),
                    user.getBannerPhotoUrl(),
                    user.getProfilePhotoUrl(),
                    user.getIsBlocked(),
                    user.getIsDeleted()
            );
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                return null;
            }
            else {
                throw e;
            }
        }
    }

    @Override
    public UserInfo getUserById(Integer userId) {
        try {
            GetUserByIdRequest request = GetUserByIdRequest.newBuilder().setId(userId).build();
            GetUserByIdResponse response = userServiceStub.getUserById(request);
            var user = response.getUser();

            return new UserInfo(
                    user.getId(),
                    user.getName(),
                    user.getNickname(),
                    user.getBannerPhotoUrl(),
                    user.getProfilePhotoUrl(),
                    user.getIsBlocked(),
                    user.getIsDeleted()
            );
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                return null;
            }
            else {
                throw e;
            }
        }
    }

    @Override
    public List<UserInfo> getUsersByIdList(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        try {
            GetUsersByIdListRequest request = GetUsersByIdListRequest.newBuilder()
                    .addAllId(ids)
                    .build();

            GetUsersByIdListResponse response = userServiceStub
                    .withDeadlineAfter(5, TimeUnit.SECONDS)
                    .getUsersByIdList(request);

            if (response == null || response.getUserCount() == 0) {
                return Collections.emptyList();
            }

            return response.getUserList().stream()
                    .map(user -> new UserInfo(
                            user.getId(),
                            user.getName(),
                            user.getNickname(),
                            user.getBannerPhotoUrl(),
                            user.getProfilePhotoUrl(),
                            user.getIsBlocked(),
                            user.getIsDeleted()))
                    .collect(Collectors.toList());

        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                return Collections.emptyList();
            }
            throw e;
        }
    }

}

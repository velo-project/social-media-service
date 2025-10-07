package com.github.veloproject.socialmediaservices.infrastructure.services.user;

import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGRPCClient {
    @GrpcClient("user-services")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public boolean existsByUserId(String userId) {
        ExistsByUserIdRequest request = ExistsByUserIdRequest.newBuilder().setUserId(userId).build();
        ExistsByUserIdResponse response = userServiceStub.existsByUserId(request);
        return response.getExists();
    }
}

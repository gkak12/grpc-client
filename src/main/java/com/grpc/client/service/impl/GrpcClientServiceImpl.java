package com.grpc.client.service.impl;

import com.grpc.client.service.GrpcClientService;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import com.grpc.server.GrpcServerServiceGrpc;
import io.grpc.Channel;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientServiceImpl implements GrpcClientService {

    @GrpcClient("server-service")
    private Channel serverServiceChannel;

    @Override
    public GrpcServerResponse findGrpcServerDataList(GrpcServerRequest request) {
        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);
        stub.findGrpcServerDataList(request);
        return null;
    }
}

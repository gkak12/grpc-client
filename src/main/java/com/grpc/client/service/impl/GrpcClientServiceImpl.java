package com.grpc.client.service.impl;

import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.mapper.GrpcMapper;
import com.grpc.client.service.GrpcClientService;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import com.grpc.server.GrpcServerServiceGrpc;
import io.grpc.Channel;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrpcClientServiceImpl implements GrpcClientService {

    @GrpcClient("server-service")
    private Channel serverServiceChannel;

    private final GrpcMapper grpcMapper;

    @Override
    public List<String> findGrpcServerDataList(RequestDto requestDto) {
        GrpcServerRequest request = grpcMapper.toGrpcServerRequest(requestDto);

        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);
        GrpcServerResponse response = stub.findGrpcServerObjects(request);

        return response.getObjectsList().stream()
                .toList();
    }
}

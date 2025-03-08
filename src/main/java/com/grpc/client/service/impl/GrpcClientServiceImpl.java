package com.grpc.client.service.impl;

import com.grpc.client.GrpcServerRequest;
import com.grpc.client.GrpcServerResponse;
import com.grpc.client.GrpcServerServiceGrpc;
import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.response.ResponseDto;
import com.grpc.client.domain.dto.response.ResponseObject;
import com.grpc.client.domain.mapper.GrpcMapper;
import com.grpc.client.service.GrpcClientService;
import io.grpc.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcClientServiceImpl implements GrpcClientService {

    @GrpcClient("server-service")
    private Channel serverServiceChannel;

    private final GrpcMapper grpcMapper;

    @Override
    public ResponseDto findGrpcServerNames(RequestDto requestDto) {
        log.info("grpc-client | findGrpcServerNames requestDto: {}", requestDto);

        GrpcServerRequest request = grpcMapper.toGrpcServerRequest(requestDto);

        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);
        GrpcServerResponse response = stub.findGrpcServerNames(request);

        return ResponseDto.builder()
                .objects(
                    response.getNamesList().stream().toList()
                )
                .build();
    }

    @Override
    public List<ResponseObject> findGrpcServerObjects(RequestDto requestDto) {
        log.info("grpc-client | findGrpcServerNames requestDto: {}", requestDto);

        GrpcServerRequest request = grpcMapper.toGrpcServerRequest(requestDto);

        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);
        GrpcServerResponse response = stub.findGrpcServerObjects(request);

        List<ResponseObject> objectList = response.getObjectsList().stream()
                .map(grpcMapper::toResponseObject)
                .toList();

        return objectList;
    }
}

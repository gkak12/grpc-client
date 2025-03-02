package com.grpc.client.service;

import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;

public interface GrpcClientService {
    GrpcServerResponse findGrpcServerDataList(GrpcServerRequest request);
}

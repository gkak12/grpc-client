package com.grpc.client.service;

import com.google.protobuf.Empty;
import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.response.ResponseDto;
import com.grpc.client.domain.dto.response.ResponseObject;

import java.util.List;

public interface GrpcClientService {

    String findGrpcServerName(RequestDto requestDto);
    ResponseDto findGrpcServerNames(RequestDto requestDto);
    List<ResponseObject> findGrpcServerObjects(Empty empty);
}

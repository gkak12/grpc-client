package com.grpc.client.service;

import com.google.protobuf.Empty;
import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.response.ResponseDto;
import com.grpc.client.domain.dto.response.ResponseObject;

import java.util.List;

public interface GrpcClientService {

    String findNameFromGrpcServer(RequestDto requestDto);
    ResponseDto findNamesFromGrpcServer(RequestDto requestDto);
    List<ResponseObject> findObjectsFromGrpcServer(Empty empty);
}

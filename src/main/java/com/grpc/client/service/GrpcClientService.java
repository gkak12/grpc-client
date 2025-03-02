package com.grpc.client.service;

import com.grpc.client.domain.dto.request.RequestDto;

import java.util.List;

public interface GrpcClientService {

    List<String> findGrpcServerDataList(RequestDto requestDto);
}

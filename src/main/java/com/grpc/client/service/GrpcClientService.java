package com.grpc.client.service;

import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.response.ResponseDto;

public interface GrpcClientService {

    ResponseDto findGrpcServerDataList(RequestDto requestDto);
}

package com.grpc.client.service;

import com.google.protobuf.Empty;
import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.request.RequestFileDto;
import com.grpc.client.domain.dto.response.ResponseDto;
import com.grpc.client.domain.dto.response.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GrpcClientService {

    String findNameFromGrpcServer(RequestDto requestDto);
    ResponseDto findNamesFromGrpcServer(RequestDto requestDto);
    List<ResponseObject> findObjectsFromGrpcServer(Empty empty);
    String uploadFileToGrpcServer(RequestFileDto requestFileDto);
    ResponseEntity<byte[]> downloadFileFromGrpcServer(RequestDto requestDto);
}

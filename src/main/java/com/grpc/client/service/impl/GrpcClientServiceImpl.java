package com.grpc.client.service.impl;

import com.google.protobuf.Empty;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcClientServiceImpl implements GrpcClientService {

    @GrpcClient("server-service")
    private Channel serverServiceChannel;

    private final GrpcMapper grpcMapper;

    @Override
    public String findNameFromGrpcServer(RequestDto requestDto) {
        log.info("grpc-client | findGrpcServerName requestDto: {}", requestDto);

        GrpcServerRequest request = grpcMapper.toGrpcServerRequest(requestDto);

        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);
        GrpcServerResponse response = stub.findNameFromGrpcServer(request);

        return response.getName();
    }

    @Override
    public ResponseDto findNamesFromGrpcServer(RequestDto requestDto) {
        log.info("grpc-client | findGrpcServerNames requestDto: {}", requestDto);

        GrpcServerRequest request = grpcMapper.toGrpcServerRequest(requestDto);

        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);
        GrpcServerResponse response = stub.findNamesFromGrpcServer(request);

        return ResponseDto.builder()
                .objects(
                    response.getNamesList().stream().toList()
                )
                .build();
    }

    @Override
    public List<ResponseObject> findObjectsFromGrpcServer(Empty empty) {
        log.info("grpc-client | findGrpcServerNames requestDto: empty");

        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);
        GrpcServerResponse response = stub.findObjectsFromGrpcServer(empty);

        return response.getObjectsList().stream()
                .map(grpcMapper::toResponseObject)
                .toList();
    }

    @Override
    public ResponseEntity<byte[]> downloadFileFromGrpcServer(RequestDto requestDto) {
        log.info("grpc-client | findGrpcServerNames requestDto: empty");

        GrpcServerRequest request = grpcMapper.toGrpcServerRequest(requestDto);
        GrpcServerServiceGrpc.GrpcServerServiceBlockingStub stub = GrpcServerServiceGrpc.newBlockingStub(serverServiceChannel);

        List<byte[]> chunkList = new ArrayList<>();

        stub.downloadFileFromGrpcServer(request)
            .forEachRemaining(chunk -> {
                chunkList.add(chunk.getData().toByteArray());
            });

        int totalSize = chunkList.stream().mapToInt(b -> b.length).sum();
        byte[] fileData = new byte[totalSize];
        int offset = 0;
        for (byte[] chunk : chunkList) {
            System.arraycopy(chunk, 0, fileData, offset, chunk.length);
            offset += chunk.length;
        }

        return new ResponseEntity<>(fileData, getHttpHeaders(requestDto.getKeyword()), HttpStatus.OK);
    }

    private HttpHeaders getHttpHeaders(String fileName) {
        String contentType = "application/octet-stream";

        if (fileName.endsWith(".txt")) {
            contentType = "text/plain";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            contentType = "image/png";
        } else if (fileName.endsWith(".pdf")) {
            contentType = "application/pdf";
        }

        String encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename);
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        return headers;
    }
}

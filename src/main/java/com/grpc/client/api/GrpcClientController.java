package com.grpc.client.api;

import com.google.protobuf.Empty;
import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.response.ResponseDto;
import com.grpc.client.domain.dto.response.ResponseObject;
import com.grpc.client.service.GrpcClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/grpc")
@RequiredArgsConstructor
public class GrpcClientController {

    private final GrpcClientService grpcClientService;

    @GetMapping("/findGrpcServerName.do")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> findGrpcServerName(@ParameterObject @Valid RequestDto requestDto) {
        log.info("grpc-client | GrpcClientController findGrpcServerName is called.");

        return ResponseEntity.ok(
                grpcClientService.findGrpcServerName(requestDto)
        );
    }

    @GetMapping("/findGrpcServerNames.do")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> findGrpcServerNames(@ParameterObject @Valid RequestDto requestDto) {
        log.info("grpc-client | GrpcClientController findGrpcServerNames is called.");

        return ResponseEntity.ok(
            grpcClientService.findGrpcServerNames(requestDto)
        );
    }

    @GetMapping("/findGrpcServerObjects.do")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResponseObject>> findGrpcServerObjects(Empty empty) {
        log.info("grpc-client | GrpcClientController findGrpcServerObjects is called.");

        return ResponseEntity.ok(
            grpcClientService.findGrpcServerObjects(empty)
        );
    }
}

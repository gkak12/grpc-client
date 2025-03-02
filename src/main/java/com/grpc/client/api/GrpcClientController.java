package com.grpc.client.api;

import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.service.GrpcClientService;
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

    @GetMapping("/findGrpcServerDataList.do")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<String>> findGrpcServerDataList(@ParameterObject RequestDto requestDto) {
        return ResponseEntity.ok(
                grpcClientService.findGrpcServerDataList(requestDto)
        );
    }
}

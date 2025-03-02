package com.grpc.client.api;

import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.response.ResponseDto;
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

@Slf4j
@RestController
@RequestMapping("/grpc")
@RequiredArgsConstructor
public class GrpcClientController {

    private final GrpcClientService grpcClientService;

    @GetMapping("/findGrpcServerDataList.do")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> findGrpcServerDataList(@ParameterObject @Valid RequestDto requestDto) {
        log.info("grpc-client | GrpcClientController findGrpcServerDataList is called.");

        return ResponseEntity.ok(
                grpcClientService.findGrpcServerDataList(requestDto)
        );
    }
}

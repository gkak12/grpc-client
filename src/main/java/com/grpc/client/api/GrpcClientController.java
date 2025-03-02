package com.grpc.client.api;

import com.grpc.client.service.GrpcClientService;
import com.grpc.server.GrpcServerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public void findGrpcServerDataList(){
        GrpcServerRequest request = GrpcServerRequest.newBuilder()
            .setSeq(1L)
            .build();

        grpcClientService.findGrpcServerDataList(request);
    }
}

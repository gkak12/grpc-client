package com.grpc.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.grpc.client")
public class GrpcClientApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplicaton.class, args);
    }
}
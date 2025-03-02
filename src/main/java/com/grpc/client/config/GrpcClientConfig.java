package com.grpc.client.config;

import net.devh.boot.grpc.client.config.GrpcChannelProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "grpc.client")
public class GrpcClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "grpc.client.server-service")
    public GrpcChannelProperties userServiceChannelProperties() {
        return new GrpcChannelProperties();
    }
}

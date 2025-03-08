package com.grpc.client.domain.mapper;

import com.grpc.client.GrpcObject;
import com.grpc.client.GrpcServerRequest;
import com.grpc.client.domain.dto.request.RequestDto;
import com.grpc.client.domain.dto.response.ResponseObject;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface GrpcMapper {

    GrpcServerRequest toGrpcServerRequest(RequestDto requestDto);
    ResponseObject toResponseObject(GrpcObject grpcObject);
}

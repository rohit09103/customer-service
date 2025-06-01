package com.localhost.customer.factory;

import io.grpc.protobuf.services.ProtoReflectionService;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class ReflectionFactory {

    @Singleton
    ProtoReflectionService reflectionService() {
        return (ProtoReflectionService) ProtoReflectionService.newInstance();
    }
}

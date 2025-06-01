package com.localhost.customer;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class CustomerServiceTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        //TODO 
    	Assertions.assertTrue(application.isRunning());
    }

}

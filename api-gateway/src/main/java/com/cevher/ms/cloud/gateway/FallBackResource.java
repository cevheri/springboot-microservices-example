package com.cevher.ms.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * We use FallBack URL for Circuit Breaker
 */
@RestController
public class FallBackResource {

    /**
     * For User Microservices CircuitBreaker
     * @return FallBack Message
     */
    @GetMapping("/userServiceFallBack")
    public String userServiceFallBack(){
        return "FallBack User Controller!.. User Service is taking longer then Expected.";
    }

    /**
     * For Department Microservices CircuitBreaker
     * @return FallBack Message
     */
    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBack(){
        return "FallBack Department Controller!.. Department Service is taking longer then Expected.";
    }

    /**
     * For Task Microservices CircuitBreaker
     * @return FallBack Message
     */
    @GetMapping("/taskServiceFallBack")
    public String taskServiceFallBack(){
        return "FallBack Task Controller!.. Task Service is taking longer then Expected.";
    }
}

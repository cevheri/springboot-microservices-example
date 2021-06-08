package com.cevher.ms.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBack(){
        return "FallBack User Controller!.. User Service is taking longer then Expected.";
    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBack(){
        return "FallBack Department Controller!.. Department Service is taking longer then Expected.";
    }
    @GetMapping("/taskServiceFallBack")
    public String taskServiceFallBack(){
        return "FallBack Task Controller!.. Task Service is taking longer then Expected.";
    }
}

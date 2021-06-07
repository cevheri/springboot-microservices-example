package com.cevher.ms.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBack(){
        return "User Service is taking longer then Expected."+
                "Please try again later!..";
    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBack(){
        return "Department Service is taking longer then Expected."+
                "Please try again later!..";
    }
}

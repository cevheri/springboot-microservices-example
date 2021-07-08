package com.cevher.ms.person.config;

import com.cevher.ms.person.vm.DepartmentVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Looking up service instances with Spring DiscoveryClient. The Spring DiscoveryClient offers
 * the lowest level of access to Ribbon and the services registered within it. Using the DiscoveryClient,
 * you can query for all the services registered with the ribbon client and their corresponding URLs.
 * Next, you’ll build a simple example of using the DiscoveryClient to retrieve one of
 * the organization service URLs from Ribbon and then call the service using a standard RestTemplate class.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DepartmentDiscoveryClient {

    //    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    public DepartmentVM getDepartment(String departmentId) {
//        RestTemplate restTemplate = new RestTemplate();
//        List<ServiceInstance> instances = discoveryClient.getInstances("department-service");
//
//        if (instances.size() == 0) return null;
//        String serviceUri = String.format("%s/v1/departments/%s", instances.get(0).getUri().toString(), departmentId);
//        log.info("!!!! SERVICE URI:  " + serviceUri);

        //for Load Balancing
        val serviceUri = "http://department-service/v1/departments/{department-id}";
        return restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null,
                DepartmentVM.class,
                departmentId)
                .getBody();

    }
}

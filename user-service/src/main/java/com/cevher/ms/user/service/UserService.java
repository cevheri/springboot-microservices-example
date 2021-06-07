package com.cevher.ms.user.service;

import com.cevher.ms.user.domain.User;
import com.cevher.ms.user.repository.UserRepository;
import com.cevher.ms.user.vm.DepartmentVM;
import com.cevher.ms.user.vm.ResponseTempVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTempVM getUserWithDepartment(Long userId) {
        log.info("Inside getUser Department of UserService");

        ResponseTempVM vm = new ResponseTempVM();
        User user = userRepository.findByUserId(userId);

        // TODO : not production. we should use config
        // TODO : We can use Message Broker for example Apache Kafka
        DepartmentVM department = restTemplate.getForObject(
                //"http://localhost:9001/departments/" + user.getDepartmentId()
                "http://department-service/departments/" + user.getDepartmentId()
                , DepartmentVM.class);

        vm.setUser(user);
        vm.setDepartment(department);
        return vm;
    }
}

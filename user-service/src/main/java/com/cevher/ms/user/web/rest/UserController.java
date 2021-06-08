package com.cevher.ms.user.web.rest;

import com.cevher.ms.user.domain.User;
import com.cevher.ms.user.service.UserService;
import com.cevher.ms.user.vm.ResponseTempVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("saveUser by UserController");
        return userService.saveUser(user);
    }
    @GetMapping("/")
    public List<User> findAllUser() {
        log.info("findAllUser by UserController");
        return userService.findAllUser();
    }
    @GetMapping("/{id}")
    public ResponseTempVM getUserWithDepartment(
            @PathVariable("id") Long userId) {
        log.info("getUserWithDepartment by UserController");
        return userService.getUserWithDepartment(userId);
    }

}

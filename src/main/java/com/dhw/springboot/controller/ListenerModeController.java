package com.dhw.springboot.controller;


import com.dhw.springboot.designPattern.User;
import com.dhw.springboot.designPattern.UserService;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Log4j2
public class ListenerModeController {

    @Resource
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) throws HystrixTimeoutException {
        log.info("addUser - main thread");
        userService.saveStudent(user);
        return "success";
    }
}

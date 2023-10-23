package com.example.tripplanner;

import com.example.tripplanner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


//@Component
public class DevRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DevRunner.class);


    private final UserService userService;


    public DevRunner(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.signUp("test", "test@mail.com", "123456");
    }
}

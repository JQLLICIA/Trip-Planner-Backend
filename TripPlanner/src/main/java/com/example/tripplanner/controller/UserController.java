package com.example.tripplanner.controller;
import com.example.tripplanner.entity.PlanEntity;
import com.example.tripplanner.model.SignUpBody;
import com.example.tripplanner.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpBody signUpBody) {
        userService.signUp(signUpBody.username(), signUpBody.email(), signUpBody.password());
    }

    @GetMapping("/plans")
    public List<PlanEntity> getPlans(@AuthenticationPrincipal User user) {
        return userService.findPlans(user.getUsername());
    }
}

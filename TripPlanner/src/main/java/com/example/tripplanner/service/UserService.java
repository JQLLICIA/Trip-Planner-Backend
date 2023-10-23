package com.example.tripplanner.service;

import com.example.tripplanner.entity.PlanEntity;
import com.example.tripplanner.entity.UserEntity;
import com.example.tripplanner.repository.UserRepository;
import com.example.tripplanner.repository.PlanRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;

    public UserService(
            UserRepository userRepository,
            PlanRepository planRepository,
            PasswordEncoder passwordEncoder,
            UserDetailsManager userDetailsManager

    ) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    @Transactional
    public void signUp(String username, String email, String password) {
        email = email.toLowerCase();
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        System.out.println("OK");
        userDetailsManager.createUser(user);
        userRepository.updateEmailByUsername(username, email);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Transactional
    public List<PlanEntity> findPlans(String username) {
        return planRepository.findByUsername(username);
    }

    @Transactional
    public void addPlan(String username, LocalDate startDate, LocalDate endDate, String city) {
        PlanEntity newPlan = new PlanEntity(null, username, startDate, endDate, city);
        planRepository.save(newPlan);
    }

    @Transactional
    public void deletePlan(Integer planId) {
        planRepository.deleteByPlanId(planId);
    }
}

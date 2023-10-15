package com.example.tripplanner.model;

public record SignUpBody(
        String username,
        String email,
        String password
) {
}

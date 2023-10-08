package com.example.tripplanner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record UserEntity(
        @Id Integer backendUserId,
        String shownUserId,
        String email,
        String password
) {
}

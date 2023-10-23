package com.example.tripplanner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("plans")
public record PlanEntity(
        @Id Integer planId,
        String username,
        LocalDate startDate,
        LocalDate endDate,
        String city
) {

}

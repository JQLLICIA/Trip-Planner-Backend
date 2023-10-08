package com.example.tripplanner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("plans")
public record PlanEntity(
        @Id Integer planId,
        Integer backendUserId,
        Date startDate,
        Date endDate,
        String city
) {

}

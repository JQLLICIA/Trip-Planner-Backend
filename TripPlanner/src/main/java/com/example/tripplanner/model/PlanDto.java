package com.example.tripplanner.model;

import com.example.tripplanner.entity.DailyTripEntity;
import com.example.tripplanner.entity.PlanEntity;

import java.time.LocalDate;
import java.util.List;

public record PlanDto(
        Integer planId,
        String username,
        LocalDate startDate,
        LocalDate endDate,
        String city,
        List<DailyTripEntity> dailyTripsInPlan

) {
    public PlanDto(PlanEntity planEntity, List<DailyTripEntity> dailyTripEntities) {
        this(planEntity.planId(), planEntity.username(), planEntity.startDate(),
                planEntity.endDate(), planEntity.city(), dailyTripEntities);
    }
}

package com.example.tripplanner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("daily_trips")
public record DailyTripEntity(
        @Id Integer dailyTripId,
        Integer planId,
        LocalDate date,
        String dailyPositions
) {

}

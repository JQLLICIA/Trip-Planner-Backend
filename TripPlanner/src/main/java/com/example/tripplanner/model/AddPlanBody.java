package com.example.tripplanner.model;

public record AddPlanBody(
        String startDateString,
        String endDateString,
        String city
) {

}

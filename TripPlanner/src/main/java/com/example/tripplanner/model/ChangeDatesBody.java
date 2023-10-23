package com.example.tripplanner.model;

public record ChangeDatesBody(
        String newStartDateString,
        String newEndDateString
) {
}

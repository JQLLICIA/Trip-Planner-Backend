package com.example.tripplanner.controller;

import com.example.tripplanner.entity.DailyTripEntity;
import com.example.tripplanner.model.ChangeDailyTripBody;
import com.example.tripplanner.model.ChangeDatesBody;
import com.example.tripplanner.model.PlanDto;
import com.example.tripplanner.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/plans/{planId}")
    public PlanDto getPlanByPlanId(@PathVariable("planId") Integer planId) {
        return planService.getPlanAndDailyTrips(planId);
    }

    @PostMapping("/plans/{planId}")
    public void changePlanDates(
            @PathVariable("planId") Integer planId,
            @RequestBody ChangeDatesBody changeDatesBody
    ) {
        LocalDate newStartDate = LocalDate.parse(changeDatesBody.newStartDateString());
        LocalDate newEndDate = LocalDate.parse(changeDatesBody.newEndDateString());
        planService.changePlanDates(planId, newStartDate, newEndDate);
    }

    @GetMapping("/plans/{planId}/{dateString}")
    public DailyTripEntity findDailyTripByDate(
            @PathVariable("planId") Integer planId,
            @PathVariable("dateString") String dateString
    ) {
        LocalDate date = LocalDate.parse(dateString);
        return planService.findDailyTripByDate(planId, date);
    }

    @PostMapping("/plans/{planId}/{dateString}")
    public void changeDailyTrip(
            @PathVariable("planId") Integer planId,
            @PathVariable("dateString") String dateString,
            @RequestBody ChangeDailyTripBody changeDailyTripBody
    ) {
        LocalDate date = LocalDate.parse(dateString);
        planService.changeDailyTrip(planId, date, changeDailyTripBody.newDailyTripPositions());
    }
}

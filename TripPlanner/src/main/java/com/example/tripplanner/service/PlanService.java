package com.example.tripplanner.service;

import com.example.tripplanner.entity.DailyTripEntity;
import com.example.tripplanner.entity.PlanEntity;
import com.example.tripplanner.model.PlanDto;
import com.example.tripplanner.repository.DailyTripRepository;
import com.example.tripplanner.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final DailyTripRepository dailyTripRepository;

    public PlanService(
            PlanRepository planRepository,
            DailyTripRepository dailyTripRepository) {
        this.planRepository = planRepository;
        this.dailyTripRepository = dailyTripRepository;
    }

    @Transactional
    public PlanDto getPlanAndDailyTrips(Integer planId) {
        PlanEntity plan = planRepository.getByPlanId(planId);
        List<DailyTripEntity> dailyTrips = dailyTripRepository.findByPlanId(planId);
        return new PlanDto(plan, dailyTrips);
    }

    @Transactional
    public DailyTripEntity findDailyTripByDate(Integer planId, LocalDate date) {
        return dailyTripRepository.findByPlanIdAndDate(planId, date);
    }

    @Transactional
    public void changeDailyTrip(Integer planId, LocalDate date, String dailyPositions) {
        PlanEntity plan = planRepository.getByPlanId(planId);
        LocalDate startDate = plan.startDate();
        LocalDate endDate = plan.endDate();
        if (!date.isBefore(startDate) && !date.isAfter(endDate))
        {
            DailyTripEntity newDailyTrip = new DailyTripEntity(null, planId, date, dailyPositions);
            dailyTripRepository.save(newDailyTrip);
        }
    }

    @Transactional
    public void changePlanDates(Integer planId, LocalDate newStartDate, LocalDate newEndDate) {
        PlanEntity plan = planRepository.getByPlanId(planId);
        LocalDate startDate = plan.startDate();
        LocalDate endDate = plan.endDate();
        if (newStartDate.isAfter(startDate))
        {
            dailyTripRepository.deleteByTimePeriod(startDate, newStartDate.minusDays(1));
        }
        if (newEndDate.isBefore(endDate))
        {
            dailyTripRepository.deleteByTimePeriod(newEndDate.plusDays(1), newEndDate);
        }
        planRepository.updateByPlanId(planId, newStartDate, newEndDate);
    }

}

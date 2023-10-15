package com.example.tripplanner.repository;

import com.example.tripplanner.entity.DailyTripEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface DailyTripRepository extends ListCrudRepository<DailyTripEntity, Integer>{

    DailyTripEntity getByDailyTripId(Integer dailyTripId);

    List<DailyTripEntity> findByPlanId(Integer planId);

    DailyTripEntity findByPlanIdAndDate(Integer planId, LocalDate date);

    @Modifying
    @Query("UPDATE daily_trips SET daily_positions = :firstName WHERE daily_trip_id = :dailyTripId::jsonb")
    void updateByDailyTripId(Integer dailyTripId, String newDailyPositions);

    @Modifying
    @Query("DELETE FROM daily_trips WHERE date BETWEEN :start AND :end")
    void deleteByTimePeriod(LocalDate start, LocalDate end);
}

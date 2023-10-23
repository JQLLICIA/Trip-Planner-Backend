package com.example.tripplanner.repository;

import com.example.tripplanner.entity.PlanEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends ListCrudRepository<PlanEntity, Integer>{

    PlanEntity getByPlanId(Integer planId);
    List<PlanEntity> findByUsername(String username);

    @Modifying
    @Query("UPDATE plans SET start_date = :newStartDate, end_date=:newEndDate WHERE plan_id = :planId")
    void updateByPlanId(Integer planId, LocalDate newStartDate, LocalDate newEndDate);

    @Modifying
    @Query("DELETE FROM plans WHERE plan_id = :planId")
    void deleteByPlanId(Integer planId);
}

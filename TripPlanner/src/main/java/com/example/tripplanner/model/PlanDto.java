package com.example.tripplanner.model;

import com.example.tripplanner.entity.DailyTripEntity;

import java.util.Date;
import java.util.List;

public record PlanDto(
        Integer planId,
        Integer backendUserId,
        Date startDate,
        Date endDate,
        String city,
        List<DailyTripEntity> dailyTripsInPlan

) {
    /*
    * public record RestaurantDto(
        Long id,
        String name,
        String address,
        String phone,
        String imageUrl,
        List<MenuItemDto> menuItems
) {
    public RestaurantDto(RestaurantEntity entity, List<MenuItemDto> menuItems) {
        this(entity.id(), entity.name(), entity.address(), entity.phone(), entity.imageUrl(), menuItems);
    }
}*/
}

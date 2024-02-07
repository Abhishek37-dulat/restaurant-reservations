package com.pairlearning.expensetrackerapi.repositories;

import com.pairlearning.expensetrackerapi.domain.Restaurant;
import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    Restaurant findById(Integer restaurantId) throws EtResourceNotFoundException;

    Integer create(String name,  String location,List<String> cuisines,
                   Map<String, String> workingDays, int timeSlotInterval, String imageUrl, Map<String, Restaurant.TableRequest> capacity) throws EtBadRequestException;

    void update(Integer restaurantId, Restaurant restaurant) throws EtBadRequestException;

    void removeById(Integer restaurantId);
}

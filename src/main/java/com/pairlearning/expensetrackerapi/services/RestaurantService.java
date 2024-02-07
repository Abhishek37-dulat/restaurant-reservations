package com.pairlearning.expensetrackerapi.services;

import com.pairlearning.expensetrackerapi.domain.Restaurant;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface RestaurantService {
    List<Restaurant> fetchAllRestaurants(Integer userId);
    Restaurant fetchRestaurantById(Integer userId, Integer restaurantId) throws EtResourceNotFoundException;
    Restaurant addRestaurant(Integer userId, String name, String location, List<String> cuisines,
                             Map<String, String> workingDays, int timeSlotInterval, String imageUrl,
                             Map<String, Restaurant.TableRequest> capacity) throws EtBadRequestException;
    void updateRestaurant(Integer userId, Integer restaurantId, Restaurant restaurant) throws EtBadRequestException;
    void removeRestaurant(Integer userId, Integer restaurantId) throws EtResourceNotFoundException;
}

package com.pairlearning.expensetrackerapi.services;

import com.pairlearning.expensetrackerapi.domain.Restaurant;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetrackerapi.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> fetchAllRestaurants(Integer userId) {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant fetchRestaurantById(Integer userId, Integer restaurantId) throws EtResourceNotFoundException {
        System.out.println("This is a message to the console.");
        return restaurantRepository.findById(restaurantId);

    }



    @Override
    public Restaurant addRestaurant(Integer userId, String name, String location, List<String> cuisines,
                                    Map<String, String> workingDays, int timeSlotInterval,
                                    String imageUrl, Map<String, Restaurant.TableRequest> capacity) {

            int restaurantId = restaurantRepository.create(
                    name,
                    location,
                    cuisines,
                    workingDays,
                    timeSlotInterval,
                    imageUrl,
                    capacity

            );
            System.out.println(restaurantId);
            return restaurantRepository.findById(restaurantId);

    }


    @Override
    public void updateRestaurant(Integer userId, Integer restaurantId, Restaurant restaurant) throws EtBadRequestException {
            restaurantRepository.update(restaurantId, restaurant);

    }

    @Override
    public void removeRestaurant(Integer userId, Integer restaurantId) throws EtResourceNotFoundException {
            restaurantRepository.removeById(restaurantId);

    }
}

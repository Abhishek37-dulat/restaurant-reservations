package com.pairlearning.expensetrackerapi.repositories;

import com.pairlearning.expensetrackerapi.domain.Restaurant;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryTest {

    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        this.restaurantRepository = new RestaurantRepositoryImpl();
    }

    @Test
    void findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        assertNotNull(restaurants);
        // Add more assertions based on the expected behavior of findAll
    }

    @Test
    void findById() {
        Integer restaurantId = 1;
        assertThrows(EtResourceNotFoundException.class, () -> restaurantRepository.findById(restaurantId));
        // Add more assertions based on the expected behavior of findById
    }

    @Test
    void create() {
        String name = "Test Restaurant";
        String location = "Test Location";
        List<String> cuisines = Arrays.asList("Cuisine1", "Cuisine2");
        Map<String, String> workingDays = new HashMap<>();
        int timeSlotInterval = 30;
        String imageUrl = "test-image.jpg";
        Map<String, Restaurant.TableRequest> capacity = new HashMap<>();

        assertThrows(EtBadRequestException.class, () -> restaurantRepository.create(name, location, cuisines, workingDays, timeSlotInterval, imageUrl, capacity));
        // Add more assertions based on the expected behavior of create
    }


    @Test
    void removeById() {
        Integer restaurantId = 1;
        assertDoesNotThrow(() -> restaurantRepository.removeById(restaurantId));
        // Add more assertions based on the expected behavior of removeById
    }
}

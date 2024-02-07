package com.pairlearning.expensetrackerapi.resources;

import com.pairlearning.expensetrackerapi.domain.Restaurant;
import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.services.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantResource {

    @Autowired
     RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<Restaurant> restaurants = restaurantService.fetchAllRestaurants(userId);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(HttpServletRequest request,
                                                        @PathVariable("restaurantId") Integer restaurantId) {
        int userId = (Integer) request.getAttribute("userId");
        Restaurant restaurant = restaurantService.fetchRestaurantById(userId, restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Restaurant> addRestaurant(HttpServletRequest request, @RequestBody Map<String, Object> restaurantMap) {
        int userId = (Integer) request.getAttribute("userId");
        String name = (String) restaurantMap.get("name");
        String location = (String) restaurantMap.get("location");
        List<String> cuisines = (List<String>) restaurantMap.get("cuisines");
        Map<String, String> workingDays = (Map<String, String>) restaurantMap.get("workingDays");
        int timeSlotInterval = (int) restaurantMap.get("timeSlotInterval");
        String imageUrl = (String) restaurantMap.get("imageUrl");
        Map<String, Restaurant.TableRequest> capacity = (Map<String, Restaurant.TableRequest>) restaurantMap.get("capacity");


        try {
            Restaurant restaurant = restaurantService.addRestaurant(userId, name, location, cuisines, workingDays, timeSlotInterval, imageUrl, capacity);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        } catch (EtBadRequestException ex) {
            // Handle bad request exception
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{restaurantId}")
    public ResponseEntity<Map<String, Boolean>> updateRestaurant(HttpServletRequest request,
                                                                 @PathVariable("restaurantId") Integer restaurantId,
                                                                 @RequestBody Restaurant restaurant) {
        int userId = (Integer) request.getAttribute("userId");
        restaurantService.updateRestaurant(userId, restaurantId, restaurant);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Map<String, Boolean>> deleteRestaurant(HttpServletRequest request,
                                                                 @PathVariable("restaurantId") Integer restaurantId) {
        int userId = (Integer) request.getAttribute("userId");
        restaurantService.removeRestaurant(userId, restaurantId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

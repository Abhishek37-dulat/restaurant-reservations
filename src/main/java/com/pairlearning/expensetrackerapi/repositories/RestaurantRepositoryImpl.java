package com.pairlearning.expensetrackerapi.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pairlearning.expensetrackerapi.domain.Restaurant;
import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private static final String SQL_FIND_ALL_RESTAURANTS = "SELECT R.RESTAURANT_ID, R.NAME, R.LOCATION, " +
            "COALESCE(COUNT(T.TABLE_ID), 0) TOTAL_TABLES, R.IMAGE_URL " +
            "FROM ET_RESTAURANTS R LEFT OUTER JOIN ET_TABLES T ON R.RESTAURANT_ID = T.RESTAURANT_ID " +
            "GROUP BY R.RESTAURANT_ID";

    private static final String SQL_FIND_BY_ID_RESTAURANT = "SELECT *" +
            "FROM ET_RESTAURANTS " +
            "WHERE RESTAURANT_ID = ? ";

    private static final String SQL_CREATE_RESTAURANT = "INSERT INTO ET_RESTAURANTS (RESTAURANT_ID, NAME, LOCATION, CUISINES, WORKING_DAYS, TIME_SLOT_INTERVAL, CAPACITY, IMAGE_URL) " +
            "VALUES (NEXTVAL('ET_RESTAURANTS_SEQ'), ?, ?, ARRAY[?]::VARCHAR[], ?::jsonb, ?, ?::jsonb, ?)";

    private static final String SQL_UPDATE_RESTAURANT = "UPDATE ET_RESTAURANTS SET NAME=?, LOCATION=? WHERE RESTAURANT_ID=?";

    private static final String SQL_REMOVE_BY_ID_RESTAURANT = "DELETE FROM ET_RESTAURANTS WHERE RESTAURANT_ID=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Restaurant> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_RESTAURANTS, restaurantRowMapper);
    }

    @Override
    public Restaurant findById(Integer restaurantId) throws EtResourceNotFoundException {
        try {
            System.out.println(restaurantId);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID_RESTAURANT, new Object[]{restaurantId}, restaurantRowMapper);
        } catch (Exception e) {
            System.out.println("Error from id: "+e);
            throw new EtResourceNotFoundException("Restaurant not found");
        }
    }

    @Override
    public Integer create(String name,  String location,List<String> cuisines,
                          Map<String, String> workingDays, int timeSlotInterval, String imageUrl, Map<String, Restaurant.TableRequest> capacity) throws EtBadRequestException {

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_RESTAURANT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, location);
                Array cuisinesArray = connection.createArrayOf("varchar", cuisines.toArray());
                ps.setArray(3, cuisinesArray);
                try {
                    ps.setString(4, new ObjectMapper().writeValueAsString(workingDays));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                ps.setInt(5, timeSlotInterval);
                ps.setString(6, imageUrl);
                try {
                    ps.setString(7, new ObjectMapper().writeValueAsString(capacity));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                System.out.println(ps);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("RESTAURANT_ID");
        } catch (Exception e) {
            throw new RuntimeException("Invalid request", e);
        }
    }

    @Override
    public void update(Integer restaurantId, Restaurant restaurant) throws EtResourceNotFoundException {
        try {
            jdbcTemplate.update(SQL_UPDATE_RESTAURANT, new Object[]{
                    restaurant.getName(),
                    restaurant.getLocation(),
                    new ObjectMapper().writeValueAsString(restaurant.getCuisines()),
                    new ObjectMapper().writeValueAsString(restaurant.getWorkingDays()),
                    restaurant.getTimeSlotInterval(),
                    restaurant.getImageUrl(),
                    new ObjectMapper().writeValueAsString(restaurant.getCapacity()),

                    restaurantId
            });
        } catch (Exception e) {
            throw new RuntimeException("Invalid request", e);
        }
    }

    @Override
    public void removeById(Integer restaurantId) {
        try {
            jdbcTemplate.update(SQL_REMOVE_BY_ID_RESTAURANT, new Object[]{restaurantId});
        } catch (Exception e) {
            throw new RuntimeException("Invalid request", e);
        }
    }

    private RowMapper<Restaurant> restaurantRowMapper = ((rs, rowNum) -> {
        Restaurant restaurant = new Restaurant(
                rs.getInt("RESTAURANT_ID"),
                rs.getString("NAME"),
                rs.getString("LOCATION"),
                parseJsonArray(rs.getString("CUISINES"), String[].class),
                parseJsonObject(rs.getString("WORKING_DAYS"), Map.class),
                rs.getInt("TIME_SLOT_INTERVAL"),
                rs.getString("IMAGE_URL"),
                (Map<String, Restaurant.TableRequest>) (Map<String, Restaurant.TableRequest>) parseJsonArray(rs.getString("CAPACITY"), Table[].class)
        );
        return restaurant;
    });

    private <T> T parseJsonObject(String json, Class<T> valueType) {
        try {
            return new ObjectMapper().readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> List<T> parseJsonArray(String json, Class<T[]> arrayClass) {
        try {
            T[] array = new ObjectMapper().readValue(json, arrayClass);
            return Arrays.asList(array);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

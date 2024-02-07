package com.pairlearning.expensetrackerapi.repositories;

import com.pairlearning.expensetrackerapi.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void create() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "password123";

        // When
        Integer userId = userRepository.create(firstName,
                lastName, email, password);

        // Then
        assertNotNull(userId);
        assertTrue(userId > 0);

        // Check if the user was created successfully by retrieving it from the database
        User createdUser = userRepository.findById(userId);
        assertNotNull(createdUser);
        assertEquals(firstName, createdUser.getFirstName());
        assertEquals(lastName, createdUser.getLastName());
        assertEquals(email, createdUser.getEmail());

    }

    @Test
    void findByEmailAndPassword()  {

        String email = "john.doe@example.com";
        String password = "password123";

        User foundUser = userRepository.findByEmailAndPassword(email, password);

        assertNull(foundUser);
    }

    @Test
    void getCountByEmail() {

        String email = "john.doe@example.com";

        Integer count = userRepository.getCountByEmail(email);

        assertNotNull(count);
        assertEquals(1, count);
    }

    @Test
    void findById() {
        Integer userId = 1;

        User foundUser = userRepository.findById(userId);

        assertNull(foundUser);
    }
}

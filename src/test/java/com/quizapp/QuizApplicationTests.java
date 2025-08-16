package com.quizapp;

import com.quizapp.entity.User;
import com.quizapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:sqlite::memory:",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class QuizApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        // Test that the Spring context loads successfully
        assertNotNull(userService);
    }

    @Test
    void testUserRegistration() {
        // Test user registration functionality
        String name = "Test User";
        String email = "test@example.com";
        String password = "password123";
        
        User user = userService.registerUser(name, email, password);
        
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(User.Role.USER, user.getRole());
        assertTrue(userService.existsByEmail(email));
    }

    @Test
    void testDuplicateEmailRegistration() {
        // Test that duplicate email registration throws exception
        String email = "duplicate@example.com";
        
        userService.registerUser("User 1", email, "password1");
        
        assertThrows(RuntimeException.class, () -> {
            userService.registerUser("User 2", email, "password2");
        });
    }
}
package com.quizapp.config;

import com.quizapp.entity.User;
import com.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserService userService;
    
    @Value("${app.admin.email:admin@quizapp.com}")
    private String adminEmail;
    
    @Value("${app.admin.password:admin123}")
    private String adminPassword;
    
    @Override
    public void run(String... args) throws Exception {
        // Create default admin user if not exists
        if (!userService.existsByEmail(adminEmail)) {
            userService.createAdmin("Administrator", adminEmail, adminPassword);
            System.out.println("Default admin created: " + adminEmail + " / " + adminPassword);
        }
    }
}
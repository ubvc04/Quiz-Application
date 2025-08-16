package com.quizapp.service;

import com.quizapp.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendQuizResult(Result result) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(result.getUser().getEmail());
            message.setSubject("Quiz Result - " + result.getQuiz().getTitle());
            message.setText(buildResultMessage(result));
            
            mailSender.send(message);
        } catch (Exception e) {
            // Log error but don't fail the quiz submission
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
    private String buildResultMessage(Result result) {
        return String.format(
            "Dear %s,\n\n" +
            "You have completed the quiz: %s\n\n" +
            "Your Score: %d out of %d (%.1f%%)\n\n" +
            "Thank you for taking the quiz!\n\n" +
            "Best regards,\n" +
            "Quiz App Team",
            result.getUser().getName(),
            result.getQuiz().getTitle(),
            result.getScore(),
            result.getTotalQuestions(),
            result.getPercentage()
        );
    }
}
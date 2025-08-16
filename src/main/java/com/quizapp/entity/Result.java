package com.quizapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    private int score;
    private int totalQuestions;
    private LocalDateTime dateTaken;

    // Constructors
    public Result() {
        this.dateTaken = LocalDateTime.now();
    }

    public Result(User user, Quiz quiz, int score, int totalQuestions) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.dateTaken = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public LocalDateTime getDateTaken() { return dateTaken; }
    public void setDateTaken(LocalDateTime dateTaken) { this.dateTaken = dateTaken; }

    public double getPercentage() {
        return totalQuestions > 0 ? (double) score / totalQuestions * 100 : 0;
    }
}
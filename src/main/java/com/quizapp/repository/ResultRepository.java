package com.quizapp.repository;

import com.quizapp.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUserIdOrderByDateTakenDesc(Long userId);
    
    @Query("SELECT r FROM Result r JOIN FETCH r.user JOIN FETCH r.quiz ORDER BY r.dateTaken DESC")
    List<Result> findAllWithUserAndQuiz();
}
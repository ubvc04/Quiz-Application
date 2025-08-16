package com.quizapp.service;

import com.quizapp.entity.Result;
import com.quizapp.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    
    @Autowired
    private ResultRepository resultRepository;
    
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }
    
    public List<Result> getUserResults(Long userId) {
        return resultRepository.findByUserIdOrderByDateTakenDesc(userId);
    }
    
    public List<Result> getAllResults() {
        return resultRepository.findAllWithUserAndQuiz();
    }
}
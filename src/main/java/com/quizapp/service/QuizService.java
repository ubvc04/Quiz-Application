package com.quizapp.service;

import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }
    
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
    
    @Transactional
    public void deleteQuiz(Long id) {
        questionRepository.deleteByQuizId(id);
        quizRepository.deleteById(id);
    }
    
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
    
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    
    public int calculateScore(Long quizId, List<String> userAnswers) {
        List<Question> questions = questionRepository.findByQuizId(quizId);
        int score = 0;
        
        for (int i = 0; i < questions.size() && i < userAnswers.size(); i++) {
            if (questions.get(i).getCorrectOption().equals(userAnswers.get(i))) {
                score++;
            }
        }
        
        return score;
    }
}
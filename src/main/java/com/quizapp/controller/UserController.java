package com.quizapp.controller;

import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.Result;
import com.quizapp.entity.User;
import com.quizapp.service.EmailService;
import com.quizapp.service.QuizService;
import com.quizapp.service.ResultService;
import com.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    
    @Autowired
    private QuizService quizService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ResultService resultService;
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "user/dashboard";
    }
    
    @GetMapping("/quiz/{id}")
    public String takeQuiz(@PathVariable Long id, Model model) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isEmpty()) {
            return "redirect:/dashboard?error=Quiz not found";
        }
        
        List<Question> questions = quizService.getQuestionsByQuizId(id);
        model.addAttribute("quiz", quiz.get());
        model.addAttribute("questions", questions);
        return "user/take-quiz";
    }
    
    @PostMapping("/quiz/{id}/submit")
    public String submitQuiz(@PathVariable Long id, 
                           @RequestParam List<String> answers,
                           Authentication auth, Model model) {
        Optional<Quiz> quizOpt = quizService.getQuizById(id);
        Optional<User> userOpt = userService.findByEmail(auth.getName());
        
        if (quizOpt.isEmpty() || userOpt.isEmpty()) {
            return "redirect:/dashboard?error=Error processing quiz";
        }
        
        Quiz quiz = quizOpt.get();
        User user = userOpt.get();
        List<Question> questions = quizService.getQuestionsByQuizId(id);
        
        int score = quizService.calculateScore(id, answers);
        Result result = new Result(user, quiz, score, questions.size());
        resultService.saveResult(result);
        
        // Send email notification
        emailService.sendQuizResult(result);
        
        model.addAttribute("result", result);
        model.addAttribute("questions", questions);
        model.addAttribute("userAnswers", answers);
        return "user/quiz-result";
    }
    
    @GetMapping("/results")
    public String viewResults(Authentication auth, Model model) {
        Optional<User> userOpt = userService.findByEmail(auth.getName());
        if (userOpt.isEmpty()) {
            return "redirect:/dashboard";
        }
        
        List<Result> results = resultService.getUserResults(userOpt.get().getId());
        model.addAttribute("results", results);
        return "user/results";
    }
}
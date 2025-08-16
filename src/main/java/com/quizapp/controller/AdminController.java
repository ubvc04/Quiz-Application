package com.quizapp.controller;

import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.Result;
import com.quizapp.service.QuizService;
import com.quizapp.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private QuizService quizService;
    
    @Autowired
    private ResultService resultService;
    
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        List<Result> recentResults = resultService.getAllResults();
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("recentResults", recentResults);
        return "admin/dashboard";
    }
    
    @GetMapping("/quiz/create")
    public String createQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "admin/create-quiz";
    }
    
    @PostMapping("/quiz/create")
    public String createQuiz(@Valid @ModelAttribute Quiz quiz, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/create-quiz";
        }
        
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return "redirect:/admin/quiz/" + savedQuiz.getId() + "/questions";
    }
    
    @GetMapping("/quiz/{id}/questions")
    public String manageQuestions(@PathVariable Long id, Model model) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isEmpty()) {
            return "redirect:/admin/dashboard";
        }
        
        List<Question> questions = quizService.getQuestionsByQuizId(id);
        model.addAttribute("quiz", quiz.get());
        model.addAttribute("questions", questions);
        model.addAttribute("newQuestion", new Question());
        return "admin/manage-questions";
    }
    
    @PostMapping("/quiz/{id}/questions")
    public String addQuestion(@PathVariable Long id, 
                            @Valid @ModelAttribute("newQuestion") Question question,
                            BindingResult result, Model model) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isEmpty()) {
            return "redirect:/admin/dashboard";
        }
        
        if (result.hasErrors()) {
            List<Question> questions = quizService.getQuestionsByQuizId(id);
            model.addAttribute("quiz", quiz.get());
            model.addAttribute("questions", questions);
            return "admin/manage-questions";
        }
        
        question.setQuiz(quiz.get());
        quizService.saveQuestion(question);
        return "redirect:/admin/quiz/" + id + "/questions";
    }
    
    @GetMapping("/quiz/{id}/edit")
    public String editQuizForm(@PathVariable Long id, Model model) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isEmpty()) {
            return "redirect:/admin/dashboard";
        }
        
        model.addAttribute("quiz", quiz.get());
        return "admin/edit-quiz";
    }
    
    @PostMapping("/quiz/{id}/edit")
    public String editQuiz(@PathVariable Long id, @Valid @ModelAttribute Quiz quiz, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit-quiz";
        }
        
        quiz.setId(id);
        quizService.saveQuiz(quiz);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/quiz/{id}/delete")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/question/{id}/delete")
    public String deleteQuestion(@PathVariable Long id, @RequestParam Long quizId) {
        quizService.deleteQuestion(id);
        return "redirect:/admin/quiz/" + quizId + "/questions";
    }
    
    @GetMapping("/results")
    public String viewAllResults(Model model) {
        List<Result> results = resultService.getAllResults();
        model.addAttribute("results", results);
        return "admin/results";
    }
}
package com.quizapp.config;

import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class SampleDataLoader implements CommandLineRunner {
    
    @Autowired
    private QuizService quizService;
    
    @Override
    public void run(String... args) throws Exception {
        // Only load sample data if no quizzes exist
        if (quizService.getAllQuizzes().isEmpty()) {
            createSampleQuizzes();
            System.out.println("Sample quizzes created successfully!");
        }
    }
    
    private void createSampleQuizzes() {
        // Create Java Programming Quiz
        Quiz javaQuiz = new Quiz("Java Programming Basics", "Test your knowledge of Java programming fundamentals");
        javaQuiz = quizService.saveQuiz(javaQuiz);
        
        // Add questions to Java quiz
        addJavaQuestions(javaQuiz);
        
        // Create General Knowledge Quiz
        Quiz generalQuiz = new Quiz("General Knowledge", "A mix of questions from various topics");
        generalQuiz = quizService.saveQuiz(generalQuiz);
        
        // Add questions to General Knowledge quiz
        addGeneralQuestions(generalQuiz);
        
        // Create Math Quiz
        Quiz mathQuiz = new Quiz("Basic Mathematics", "Test your mathematical skills");
        mathQuiz = quizService.saveQuiz(mathQuiz);
        
        // Add questions to Math quiz
        addMathQuestions(mathQuiz);
    }
    
    private void addJavaQuestions(Quiz quiz) {
        Question q1 = new Question(quiz, 
            "Which of the following is NOT a Java keyword?",
            "class", "interface", "implement", "extends", "C");
        quizService.saveQuestion(q1);
        
        Question q2 = new Question(quiz,
            "What is the default value of a boolean variable in Java?",
            "true", "false", "null", "0", "B");
        quizService.saveQuestion(q2);
        
        Question q3 = new Question(quiz,
            "Which method is the entry point of a Java application?",
            "start()", "main()", "run()", "init()", "B");
        quizService.saveQuestion(q3);
        
        Question q4 = new Question(quiz,
            "What does JVM stand for?",
            "Java Virtual Machine", "Java Variable Method", "Java Verified Module", "Java Version Manager", "A");
        quizService.saveQuestion(q4);
        
        Question q5 = new Question(quiz,
            "Which of these is used to handle exceptions in Java?",
            "try-catch", "if-else", "switch-case", "for-loop", "A");
        quizService.saveQuestion(q5);
    }
    
    private void addGeneralQuestions(Quiz quiz) {
        Question q1 = new Question(quiz,
            "What is the capital of France?",
            "London", "Berlin", "Paris", "Madrid", "C");
        quizService.saveQuestion(q1);
        
        Question q2 = new Question(quiz,
            "Which planet is known as the Red Planet?",
            "Venus", "Mars", "Jupiter", "Saturn", "B");
        quizService.saveQuestion(q2);
        
        Question q3 = new Question(quiz,
            "Who painted the Mona Lisa?",
            "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo", "C");
        quizService.saveQuestion(q3);
        
        Question q4 = new Question(quiz,
            "What is the largest ocean on Earth?",
            "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean", "D");
        quizService.saveQuestion(q4);
        
        Question q5 = new Question(quiz,
            "In which year did World War II end?",
            "1944", "1945", "1946", "1947", "B");
        quizService.saveQuestion(q5);
    }
    
    private void addMathQuestions(Quiz quiz) {
        Question q1 = new Question(quiz,
            "What is 15 + 27?",
            "42", "41", "43", "40", "A");
        quizService.saveQuestion(q1);
        
        Question q2 = new Question(quiz,
            "What is the square root of 64?",
            "6", "7", "8", "9", "C");
        quizService.saveQuestion(q2);
        
        Question q3 = new Question(quiz,
            "What is 12 × 8?",
            "94", "95", "96", "97", "C");
        quizService.saveQuestion(q3);
        
        Question q4 = new Question(quiz,
            "What is 100 ÷ 4?",
            "20", "25", "30", "35", "B");
        quizService.saveQuestion(q4);
        
        Question q5 = new Question(quiz,
            "What is 2³ (2 to the power of 3)?",
            "6", "8", "9", "12", "B");
        quizService.saveQuestion(q5);
    }
}
package com.example.redditclone.controller;

import com.example.redditclone.dto.ProblemDto;
import com.example.redditclone.dto.QuizDto;
import com.example.redditclone.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@Controller
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuizes(){
        return status(HttpStatus.OK).body(this.quizService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void>createQuiz(@RequestBody QuizDto quizDto){
        this.quizService.save(quizDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto>getQuizById(@PathVariable Long id){
        return status(HttpStatus.OK).body(this.quizService.getById(id));
    }

    @GetMapping("/{title}")
    public ResponseEntity<QuizDto>getQuizByTitle(@PathVariable String title){
        return status(HttpStatus.OK).body(this.quizService.findByTitle(title));
    }

    @PostMapping("/{id}/add-problem")
    public ResponseEntity<Void>addProblem(@RequestBody ProblemDto problemDto, @PathVariable Long id){
        this.quizService.addProblem(problemDto, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

package com.example.redditclone.service;

import com.example.redditclone.dto.ProblemDto;
import com.example.redditclone.dto.QuizDto;
import com.example.redditclone.exceptions.SpringRedditException;
import com.example.redditclone.model.Problem;
import com.example.redditclone.model.Quiz;
import com.example.redditclone.repository.ProblemRepository;
import com.example.redditclone.repository.QuizRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QuizService {

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    private final ProblemRepository problemRepository;

    public List<QuizDto> getAll(){
       return this.quizRepository.findAll().stream().map(x->this.modelMapper.map(x,QuizDto.class))
                .collect(Collectors.toList());
    }

    public QuizDto save(QuizDto quizDto){
        Quiz quiz = this.modelMapper.map(quizDto,Quiz.class);
        quiz.setCreated(Instant.now());
        quiz.setUpdated(Instant.now());
       Quiz quizEntity = this.quizRepository.save(quiz);
       return this.modelMapper.map(quizEntity,QuizDto.class);
    }

    public QuizDto getById(Long id){
        return this.modelMapper.map(this.quizRepository.findById(id),QuizDto.class);
    }

    public QuizDto findByTitle(String title){
        return this.modelMapper.map(this.quizRepository.findByTitle(title),QuizDto.class);
    }

    public void addProblem(ProblemDto problemDto, Long id){
        Quiz quiz = this.quizRepository.findById(id).orElseThrow(()
                ->new SpringRedditException("Quiz not found"));
        Problem problem =
                this.modelMapper.map(problemDto, Problem.class);
        problem.setCreated(Instant.now());
        problem.setUpdated(Instant.now());
        problem = this.problemRepository.saveAndFlush(problem);
        quiz.getProblems().add(problem);
        quiz = this.quizRepository.save(quiz);
    }
}

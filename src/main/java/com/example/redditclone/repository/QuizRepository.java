package com.example.redditclone.repository;

import com.example.redditclone.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Optional<Quiz>findByTitle(String title);
    Optional<Quiz> findById(Long id);
}

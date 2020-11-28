package com.example.redditclone.repository;

import com.example.redditclone.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long> {
    List<Problem>findAllByQuiz_Title(String title);
}

package com.example.redditclone.dto;

import com.example.redditclone.model.Problem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private String title;
    List<Problem> problems;
}

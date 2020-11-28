package com.example.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDto {
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String correctAnswer;
}

package com.example.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "problems")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Problem extends BaseEntity{
    @NotEmpty
    @NotNull
    private String question;
    @NotNull
    @NotEmpty
    private String answer1;
    @NotNull
    @NotEmpty
    private String answer2;
    @NotNull
    @NotEmpty
    private String answer3;
    @NotNull
    @NotEmpty
    private String correctAnswer;
    @Nullable
    private Instant created;
    @Nullable
    private Instant updated;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
}

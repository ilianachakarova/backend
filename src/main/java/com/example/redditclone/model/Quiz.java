package com.example.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "quizes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz extends BaseEntity{
    @NotEmpty
    private String title;
    @Nullable
    private Instant created;
    @Nullable
    private Instant updated;
    @OneToMany(fetch = FetchType.EAGER)
    List<Problem>problems;
}

package com.example.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = "verification_token")
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken extends BaseEntity {
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Instant expiryDate;
}

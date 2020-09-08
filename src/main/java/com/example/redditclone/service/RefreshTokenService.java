package com.example.redditclone.service;

import com.example.redditclone.exceptions.SpringRedditException;
import com.example.redditclone.model.RefreshToken;
import com.example.redditclone.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    public void validateRefreshToken(String token){
        this.refreshTokenRepository.findByToken(token)
                .orElseThrow(()->new SpringRedditException("Refresh token not found"));
    }

    public void deleteToken(String token){
        this.refreshTokenRepository.deleteByToken(token);
    }
}

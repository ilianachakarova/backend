package com.example.redditclone.controller;

import com.example.redditclone.dto.VoteDto;
import com.example.redditclone.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@AllArgsConstructor
@CrossOrigin
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void>vote(@RequestBody VoteDto voteDto){
        this.voteService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}

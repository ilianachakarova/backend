package com.example.redditclone.controller;

import com.example.redditclone.dto.CommentDto;
import com.example.redditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@CrossOrigin
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        this.commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public void getAllCommentsForPost(@PathVariable Long postId) {
        ResponseEntity.status(HttpStatus.OK)
                .body(this.commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{userName}")
    public void getAllCommentsForUser(@PathVariable String userName){
        ResponseEntity.status(HttpStatus.OK)
                .body(
        this.commentService.getAllCommentsForUser(userName));
    }


}

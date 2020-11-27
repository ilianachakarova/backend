package com.example.redditclone.controller;

import com.example.redditclone.dto.TopicDto;
import com.example.redditclone.model.Topic;
import com.example.redditclone.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
@AllArgsConstructor
@Slf4j
//@CrossOrigin(origins = "https://angularspringblog.herokuapp.com")

public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicDto topicDto){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(topicService.save(topicDto));
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>>getAllTopics(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(topicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto>getTopic(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(topicService.getTopic(id));
    }


}

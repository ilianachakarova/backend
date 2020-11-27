package com.example.redditclone.service;

import com.example.redditclone.dto.TopicDto;
import com.example.redditclone.exceptions.SpringRedditException;
import com.example.redditclone.model.Topic;
import com.example.redditclone.repository.TopicRepository;
import com.example.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Topic save(TopicDto topicDto) {
        Topic save = this.modelMapper.map(topicDto, Topic.class);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.example.redditclone.model.User user =
                this.userRepository.findByUsername(principal.getUsername()).
                        orElseThrow(()->new UsernameNotFoundException("no such user"));
        save.setUser(user);
        save.setCreatedDate(Instant.now());
        return this.topicRepository.save(save);

    }

    @Transactional(readOnly = true)
    public List<TopicDto> getAll() {
        return topicRepository.findAll()
                .stream()
                .map(s->this.modelMapper.map(s, TopicDto.class))
                .collect(toList());
    }

    public TopicDto getTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return this.modelMapper.map(topic, TopicDto.class);
    }

    private Topic mapSubredditDto(TopicDto topicDto) {
        return Topic.builder().name(topicDto.getName())
                .description(topicDto.getDescription())
                .build();
    }


}

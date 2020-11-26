package com.example.redditclone.service;

import com.example.redditclone.dto.SubredditDto;
import com.example.redditclone.exceptions.SpringRedditException;
import com.example.redditclone.model.Topic;
import com.example.redditclone.repository.SubredditRepository;
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
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Topic save(SubredditDto subredditDto) {
        Topic save = this.modelMapper.map(subredditDto, Topic.class);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.example.redditclone.model.User user =
                this.userRepository.findByUsername(principal.getUsername()).
                        orElseThrow(()->new UsernameNotFoundException("no such user"));
        save.setUser(user);
        save.setCreatedDate(Instant.now());
        return this.subredditRepository.save(save);

    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(s->this.modelMapper.map(s,SubredditDto.class))
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Topic topic = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return this.modelMapper.map(topic,SubredditDto.class);
    }

    private Topic mapSubredditDto(SubredditDto subredditDto) {
        return Topic.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }


}

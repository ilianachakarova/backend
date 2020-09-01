package com.example.redditclone.service;

import com.example.redditclone.dto.SubredditDto;
import com.example.redditclone.exceptions.SpringRedditException;
import com.example.redditclone.model.Subreddit;
import com.example.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(this.modelMapper.map(subredditDto,Subreddit.class));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(s->this.modelMapper.map(s,SubredditDto.class))
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return this.modelMapper.map(subreddit,SubredditDto.class);
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }


}

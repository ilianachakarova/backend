package com.example.redditclone.service;

import com.example.redditclone.dto.VoteDto;
import com.example.redditclone.exceptions.PostNotFoundException;
import com.example.redditclone.exceptions.SpringRedditException;
import com.example.redditclone.model.Post;
import com.example.redditclone.model.Vote;
import com.example.redditclone.model.VoteType;
import com.example.redditclone.repository.PostRepository;
import com.example.redditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final ModelMapper modelMapper;
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;


    public void vote(VoteDto voteDto) {
        Post post = this.postRepository.findById(voteDto.getPostId())
                .orElseThrow(()->new PostNotFoundException("No post with this id"));
        Optional<Vote>vote = this.voteRepository.findTopByPostAndUserOrderByIdDesc(post,authService.getCurrentUser());
        if(vote.isPresent() && vote.get().getVoteType().equals(voteDto.getVoteType())){
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d this post");
        }
        if(VoteType.UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount()+1);
        }else {
            post.setVoteCount(post.getVoteCount()-1);
        }

        Vote entity = this.modelMapper.map(voteDto,Vote.class);
        entity.setPost(post);
        entity.setUser(this.authService.getCurrentUser());
        this.voteRepository.save(entity);
        this.postRepository.save(post);
    }
}

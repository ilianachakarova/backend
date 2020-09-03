package com.example.redditclone.service;

import com.example.redditclone.dto.CommentDto;
import com.example.redditclone.model.Post;
import com.example.redditclone.repository.PostRepository;
import com.example.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public void save(CommentDto commentDto){
       Post post = postRepository.findById(commentDto.getPostId())
               .orElseThrow(()->new UsernameNotFoundException("No such  post"));
        //to be continued
    }
}

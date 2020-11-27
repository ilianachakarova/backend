package com.example.redditclone.service;

import com.example.redditclone.dto.PostRequest;
import com.example.redditclone.dto.PostResponse;
import com.example.redditclone.exceptions.PostNotFoundException;
import com.example.redditclone.exceptions.SubredditNotFoundException;
import com.example.redditclone.model.Post;
import com.example.redditclone.model.Topic;
import com.example.redditclone.model.User;
import com.example.redditclone.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class PostService {
    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;


    public void save(PostRequest postRequest) {
        Topic topic = this.topicRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));

        Post post = this.modelMapper.map(postRequest, Post.class);
        post.setUser(authService.getCurrentUser());
        post.setTopic(topic);
        post.setCreatedDate(Instant.now());
        this.postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id){
        Post post = this.postRepository.findById(id).orElseThrow(()->new PostNotFoundException(id.toString()));
        PostResponse postResponse =  this.modelMapper.map(post, PostResponse.class);
        postResponse.setUserName(post.getUser().getUsername());
        return postResponse;
    }
    @Transactional(readOnly = true)
    public List<PostResponse>getAllPosts(){
       return this.postRepository.findAll().stream().map(post->{
            PostResponse postResponse = this.modelMapper.map(post,PostResponse.class);
            postResponse.setUserName(post.getUser().getUsername());
            postResponse.setCommentCount(this.commentRepository.findByPost(post).size());
            return postResponse;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse>getPostsBySubreddit(Long topicId){
      return this.postRepository.findByTopic_Id(topicId).stream()
                .map(post -> {
                    PostResponse postResponse = this.modelMapper.map(post,PostResponse.class);
                    postResponse.setUserName(post.getUser().getUsername());
                    return postResponse;
                }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public  List<PostResponse>findPostsByUsername(String username){
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No such user"));

        return postRepository.findPostsByUser(user)
                .stream().map(post -> {
                    PostResponse postResponse = this.modelMapper.map(post,PostResponse.class);
                    postResponse.setUserName(post.getUser().getUsername());
                    return postResponse;
                }).collect(Collectors.toList());
    }

}

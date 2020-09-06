package com.example.redditclone.service;

import com.example.redditclone.dto.CommentDto;
import com.example.redditclone.exceptions.PostNotFoundException;
import com.example.redditclone.model.Comment;
import com.example.redditclone.model.NotificationEmail;
import com.example.redditclone.model.Post;
import com.example.redditclone.repository.CommentRepository;
import com.example.redditclone.repository.PostRepository;
import com.example.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final String POST_URL ="";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    public void save(CommentDto commentDto){
       Post post = postRepository.findById(commentDto.getPostId())
               .orElseThrow(()->new UsernameNotFoundException("No such  post"));
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.example.redditclone.model.User user =
                this.userRepository.findByUsername(principal.getUsername()).
                        orElseThrow(()->new UsernameNotFoundException("no such user"));

        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setCreatedDate(Instant.now());
        comment.setPost(post);
        comment.setUser(user);
        this.commentRepository.save(comment);

        //send notification email to the user
        String message = mailContentBuilder.build
                (post.getUser().getUsername() + " posted a comment on your post" + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, com.example.redditclone.model.User user) {
        this.mailService.sendMail(
                new NotificationEmail(user.getUsername() +
                        " commented on your post",user.getEmail(),message));
    }

    public Object getAllCommentsForPost(Long postId) {
      Post post = this.postRepository.findById(postId).orElseThrow(
              ()->new PostNotFoundException("The post does not exist"));
      return this.commentRepository.findByPost(post).stream().map(comment->{
        CommentDto commentDto = this.modelMapper.map(comment,CommentDto.class);
        commentDto.setUserName(comment.getUser().getUsername());
        return commentDto;
      }).collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsForUser(String userName) {
        return this.commentRepository.findByUser_Username(userName).stream()
                .map(comment -> {
                    CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
                    commentDto.setUserName(comment.getUser().getUsername());
                    return commentDto;
                }).collect(Collectors.toList());
    }
}

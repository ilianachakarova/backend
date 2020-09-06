package com.example.redditclone.repository;

import com.example.redditclone.model.Comment;
import com.example.redditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);
    List<Comment>findByUser_Username(String username);
}

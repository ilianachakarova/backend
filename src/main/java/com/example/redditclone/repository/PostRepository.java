package com.example.redditclone.repository;

import com.example.redditclone.model.Post;
import com.example.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post>findBySubreddit_Id(Long id);
    List<Post>findPostsByUser(User user);
}

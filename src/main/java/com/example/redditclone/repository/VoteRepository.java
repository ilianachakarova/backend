package com.example.redditclone.repository;

import com.example.redditclone.model.Post;
import com.example.redditclone.model.User;
import com.example.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
   Optional<Vote> findTopByPostAndUserOrderByIdDesc(Post post, User currentUser);
   List<Vote>findByPost(Long postId);
}

package com.example.redditclone.repository;

import com.example.redditclone.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
    Optional<Topic> findByName(String topicName);
}

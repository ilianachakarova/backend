package com.example.redditclone;

import com.example.redditclone.model.Post;
import com.example.redditclone.model.Subreddit;
import com.example.redditclone.model.User;
import com.example.redditclone.repository.PostRepository;
import com.example.redditclone.repository.SubredditRepository;
import com.example.redditclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataInit implements CommandLineRunner {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    @Autowired
    public DataInit(PostRepository postRepository, SubredditRepository subredditRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {
        User user = new User("user","123456","user@gmail.com", Instant.now(),true);
        this.userRepository.save(user);
        Subreddit subreddit = new Subreddit("subreddit","sample",null,Instant.now(),user);
        this.subredditRepository.save(subreddit);
        Post post = new Post("testPost","www.url.com","asd asd asd",5,user,Instant.now(),subreddit);
        this.postRepository.save(post);

    }
}

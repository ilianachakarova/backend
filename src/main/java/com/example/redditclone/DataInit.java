package com.example.redditclone;

import com.example.redditclone.repository.PostRepository;
import com.example.redditclone.repository.SubredditRepository;
import com.example.redditclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
//        User user = new User("user","123456","user@gmail.com", Instant.now(),true);
//        this.userRepository.saveAndFlush(user);
//        Topic topic = new Topic("subreddit","sample",null,Instant.now(),user);
//        this.subredditRepository.save(topic);
//        Post post = new Post("testPost","www.url.com","asd asd asd",5,user,Instant.now(), topic);
//        this.postRepository.save(post);

    }
}

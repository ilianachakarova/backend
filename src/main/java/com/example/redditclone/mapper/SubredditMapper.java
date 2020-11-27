package com.example.redditclone.mapper;

import com.example.redditclone.dto.TopicDto;
import com.example.redditclone.model.Post;
import com.example.redditclone.model.Topic;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    TopicDto mapSubredditDto(Topic topic);

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    TopicDto mapSubredditToDto(Topic topic);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Topic mapDtoToSubreddit(TopicDto topicDto);
}

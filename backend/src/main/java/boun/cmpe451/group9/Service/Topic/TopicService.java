package boun.cmpe451.group9.Service.Topic;

import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

public interface TopicService {

    void addTopic(Topic t);

    Topic getTopicById(long id);

    void updateTopic(Topic t);

    void removeTopic(long id);

    List<Topic> getTopicsByUserId(long id);

    boolean checkTopicExistsById(long id);
}

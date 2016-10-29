package boun.cmpe451.group9.DAO.Topic;

import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

public interface TopicDAO {

    void addTopic(Topic t);

    Topic getTopicById(long id);

    void updateTopic(Topic t);

    void removeTopicById(long id);

    List<Topic> getTopicsByUserId(long id);

    boolean checkTopicExistsById(long id);
}

package boun.cmpe451.group9.Service.Topic;

import boun.cmpe451.group9.DAO.Topic.TopicDAO;
import boun.cmpe451.group9.Models.DB.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;

    @Autowired
    public void setTopicDAO(TopicDAO topicDAO){
        this.topicDAO = topicDAO;
    }

    @Override
    public void addTopic(Topic t) {
        topicDAO.addTopic(t);
    }

    @Override
    public Topic getTopicById(long id) {
        return topicDAO.getTopicById(id);
    }

    @Override
    public void updateTopic(Topic t) {
        topicDAO.updateTopic(t);
    }

    @Override
    public void removeTopic(long id) {
        topicDAO.removeTopicById(id);
    }

    @Override
    public List<Topic> getTopicsByUserId(long id) {
        return topicDAO.getTopicsByUserId(id);
    }

    @Override
    public boolean checkTopicExistsById(long id) {
        return topicDAO.checkTopicExistsById(id);
    }
}

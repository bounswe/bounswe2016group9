package boun.cmpe451.group9.Service.Topic;

import boun.cmpe451.group9.DAO.Topic.TopicDAO;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly = true)
public class TopicServiceImpl extends BaseServiceImpl<Topic> implements TopicService {

    private TopicDAO topicDAO;

    @Autowired
    public void setTopicDAO(TopicDAO topicDAO){
        this.topicDAO = topicDAO;
    }

    @Override
    public List<Topic> getTopicsByUserId(long id) {
        return topicDAO.getTopicsByUserId(id);
    }

    @Override
    public List<Topic> autoComp(String keyword) {
        return topicDAO.autoComp(keyword);
    }

    @Override
    public List<Topic> getGrappi() {
        return  topicDAO.getGrappi();
    }
}

package boun.cmpe451.group9.Service.Search;


import boun.cmpe451.group9.DAO.STagTopic.STagTopicDAO;
import boun.cmpe451.group9.DAO.Topic.TopicDAO;
import boun.cmpe451.group9.Models.DB.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService {
    private TopicDAO topicDAO;
    private STagTopicDAO sTagTopicDAO;

    @Autowired
    public void setTopicDAO(TopicDAO topicDAO){ this.topicDAO = topicDAO; }

    @Autowired
    public void setsTagTopicDAO(STagTopicDAO sTagTopicDAO){ this.sTagTopicDAO = sTagTopicDAO; }

    @Override
    public List<Topic> searchTopic(String keywords) {
        String[] strings = keywords.split(" ");

        List<Topic> topics = topicDAO.searchTopicByName(strings);
        topics.addAll(sTagTopicDAO.searchTopicBySTag(strings));

        return topics;
    }
}

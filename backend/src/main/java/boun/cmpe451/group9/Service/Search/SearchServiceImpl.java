package boun.cmpe451.group9.Service.Search;


import boun.cmpe451.group9.DAO.Post.PostDAO;
import boun.cmpe451.group9.DAO.STagTopic.STagTopicDAO;
import boun.cmpe451.group9.DAO.TagPost.TagPostDAO;
import boun.cmpe451.group9.DAO.Topic.TopicDAO;
import boun.cmpe451.group9.Models.DB.Post;
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
    private PostDAO postDAO;
    private TagPostDAO tagPostDAO;

    @Autowired
    public void setTopicDAO(TopicDAO topicDAO){ this.topicDAO = topicDAO; }

    @Autowired
    public void setsTagTopicDAO(STagTopicDAO sTagTopicDAO){ this.sTagTopicDAO = sTagTopicDAO; }

    @Autowired
    public void setPostDAO(PostDAO postDAO){ this.postDAO = postDAO; }

    @Autowired
    public void setTagPostDAO(TagPostDAO tagPostDAO){ this.tagPostDAO = tagPostDAO; }

    @Override
    public List<Topic> searchTopic(String keywords) {
        String[] strings = keywords.split(" ");

        List<Topic> topics = topicDAO.searchTopicByName(strings);
        topics.addAll(sTagTopicDAO.searchTopicBySTag(strings));

        return topics;
    }

    @Override
    public List<Post> searchPost(String keywords) {
        String[] strings = keywords.split(" ");

        List<Post> posts = postDAO.searchPostByName(strings);
        posts.addAll(tagPostDAO.searchPostByTag(strings));

        return posts;
    }
}

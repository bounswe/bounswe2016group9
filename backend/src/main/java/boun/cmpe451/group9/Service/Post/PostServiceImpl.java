
package boun.cmpe451.group9.Service.Post;

import boun.cmpe451.group9.DAO.Topic.TopicDAO;
import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import boun.cmpe451.group9.DAO.Post.PostDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly = true)
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {
    private PostDAO postDAO;
    private TopicDAO topicDAO;

    @Autowired
    public void setPostDAO(PostDAO postDAO){
        this.postDAO = postDAO;
    }

    @Autowired
    public void setTopicDAO(TopicDAO topicDAO){
        this.topicDAO = topicDAO;
    }

    @Override
    public List<Post> getPostByUserId(long id) {
        return postDAO.getPostByUserId(id);
    }

    @Override
    public List<Post> getPostByTopicId(long id) {
        return postDAO.getPostsByTopicId(id);
    }

    @Override
    public List<Post> getPostByLocation(int id) {
        return postDAO.getPostByLocation(id);
    }


    public void save(Post entity) {
        Topic topic = entity.getTopic();
        topic.setPostCount(topic.getPostCount()+1);
        topicDAO.save(topic);
        this.postDAO.save(entity);
    }


}

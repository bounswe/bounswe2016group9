package boun.cmpe451.group9.DAO.Post;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class PostDAOImpl extends BaseDAOImpl<Post> implements PostDAO {
    @Override
    public List<Post> getPostByUserId(long userID) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM post p WHERE p.created_user_id = :id")
                .addEntity(Post.class)
                .setParameter("id", userID)
                .list();
    }

    @Override
    public List<Post> getPostByLocation(int locationID) {
        return  this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM post p WHERE p.locationID = :id")
                .addEntity(Post.class)
                .setParameter("id", locationID)
                .list();
    }

    @Override
    public List<Post> getPostsByTopicId(long topicID) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM post p WHERE p.topic_id = :id")
                .addEntity(Post.class)
                .setParameter("id", topicID)
                .list();
    }
}

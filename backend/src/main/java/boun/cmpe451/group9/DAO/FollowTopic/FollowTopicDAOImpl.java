package boun.cmpe451.group9.DAO.FollowTopic;
import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Models.DB.FollowTopic;
import boun.cmpe451.group9.Models.DB.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by seha on 10.12.2016.
 */
@SuppressWarnings("unchecked")
@Repository
public class FollowTopicDAOImpl extends BaseDAOImpl<FollowTopic> implements FollowTopicDAO {
    @Override
    public List<Topic> getFollowingTopicsById(long userID) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM follow_topic r JOIN topic t ON t.id = r.topic_id WHERE r.follower_id =:id")
                .addEntity(Topic.class)
                .setParameter("id", userID)
                .list();
    }

    @Override
    public List<User> getFollowerUsersById(long topicID) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT u.* FROM follow_topic r JOIN user u ON u.id = r.follower_id WHERE r.topic_id =:id")
                .addEntity(User.class)
                .setParameter("id", topicID)
                .list();
    }

    @Override
    public boolean checkIfFollowTopicExistsByIds(long userID, long topicID) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT rel.* FROM follow_topic rel WHERE (rel.follower_id = :userID) AND (rel.topic_id = :topicID)")
                .addEntity(FollowTopic.class)
                .setParameter("userID", userID)
                .setParameter("topicID", topicID)
                .uniqueResult() != null;
    }
}

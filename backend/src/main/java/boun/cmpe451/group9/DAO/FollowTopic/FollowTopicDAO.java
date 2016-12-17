package boun.cmpe451.group9.DAO.FollowTopic;

/**
 * Created by seha on 10.12.2016.
 */

import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

/** *
 * Database access methods for the table "FOLLOW_TOPIC"
 */
 public interface FollowTopicDAO {

    /**
     * Get all topics that followed by user "userID"
     * @param userID user id
     * @return all topics that followed by user "userID"
     */
    List<Topic> getFollowingTopicsById(long userID);

    /**
     * Get all users that follow topic with "topicID"
     * @param topicID topic id
     * @return all users that follow topic with "topicID"
     */
    List<User> getFollowerUsersById(long topicID);
}

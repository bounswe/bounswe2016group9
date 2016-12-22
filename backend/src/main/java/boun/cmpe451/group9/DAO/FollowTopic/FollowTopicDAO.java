package boun.cmpe451.group9.DAO.FollowTopic;

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

    /**
     * Checks if a relation exists from "from" to "to"
     * @param userID topic the relations come from
     * @param topicID topic the relation goes to
     * @return TRUE if exists, FALSE if not
     */
    boolean checkIfFollowTopicExistsByIds(long userID, long topicID);

}

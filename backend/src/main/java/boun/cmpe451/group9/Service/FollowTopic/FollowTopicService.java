package boun.cmpe451.group9.Service.FollowTopic;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Models.DB.FollowTopic;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;
/**
 * Service methods for the resource "FollowTopic"
 * Created by seha on 10.12.2016.
 */
public interface FollowTopicService extends BaseService<FollowTopic> {

    /**
     * Retrieves all the following topics by the user "id"
     * @param id id of user
     * @return list of topics that following by user "id"
     */
    List <Topic> getFollowingTopicsById(long id);

    /**
     * Retrieves all the follower users by the topic "id"
     * @param id id of topic
     * @return list of users that followed of topic "id"
     */
    List <User> getFollowerUsersById(long id);

    /**
     *  Checks whether user "userID" is following topic "topicID" or not
     * @param userID id of user
     * @param topicID id of user
     * @return true if follows, false if not
     */
    boolean checkIfFollowTopicExistsByIds(long userID, long topicID);
}

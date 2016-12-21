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

    List <Topic> getFollowingTopicsById(long id);

    List <User> getFollowerUsersById(long id);

    boolean checkIfFollowTopicExistsByIds(long userID, long topicID);
}

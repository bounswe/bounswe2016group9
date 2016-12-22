package boun.cmpe451.group9.Service.FollowRel;


import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for the resource "FollowRel"
 */
public interface FollowRelService extends BaseService<FollowRel> {
    /**
     * Retrieves all the following users by the user "id"
     * @param id id of user
     * @return list of users that following by user "id"
     */
    List<User> getFollowingByUserId(long id);

    /**
     * Retrieves all the follower users by the user "id"
     * @param id id of user
     * @return list of users that followers of user "id"
     */
    List<User> getFollowerByUserId(long id);

    /**
     *  Checks whether user "followerID" is following user "followingID" or not
     * @param followerID id of user
     * @param followingID id of user
     * @return true if follows, false if not
     */
    boolean checkIfFollowRelExistsByIds(long followerID, long followingID);
}

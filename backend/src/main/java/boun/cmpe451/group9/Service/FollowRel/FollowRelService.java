package boun.cmpe451.group9.Service.FollowRel;


import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for the resource "FollowRel"
 */
public interface FollowRelService extends BaseService<FollowRel> {

    List<User> getFollowingByUserId(long id);

    List<User> getFollowerByUserId(long id);

    boolean checkIfFollowRelExistsByIds(long followerID, long followingID);
}

package boun.cmpe451.group9.DAO.FollowRel;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

/**
 * Database access methods for the table "FOLLOW_REL"
 */
public interface FollowRelDAO extends BaseDAO<FollowRel> {
    /**
     * Get all users that user "id" follows
     * @param id user "id"
     * @return all users that user "id" follows
     */
    List<User> getFollowingById(long id);

    /**
     * Get all users that follow user "id"
     * @param id user "id"
     * @return all users that follow user "id"
     */
    List<User> getFollowerById(long id);
}

package boun.cmpe451.group9.DAO.FollowRel;

import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

/**
 * Database access methods for the table "FOLLOW_REL"
 */
public interface FollowRelDAO {

    /**
     * Adds row into table "FOLLOW_REL"
     * @param followRel the object representation of the added row
     */
    void addFollowRel(FollowRel followRel);

    /**
     * Retrieves row with given id from table "FOLLOW_REL"
     * @param id the id of the desired row
     * @return the object representation of the retrieved row
     */
    FollowRel getFollowRelById(long id);

    /**
     * Updates the row in the table "FOLLOW_REL" with the given one
     * @param followRel the object representation of the updated row
     */
    void updateFollowRel(FollowRel followRel);

    /**
     * Removes the row from the table "FOLLOW_REL"
     * @param id the id of the row which will be deleted
     */
    void removeFollowRelById(long id);

    List<User> getfollowingById(long id);

    List<User> getfollowerById(long id);
}

package boun.cmpe451.group9.DAO.User;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.User;

/**
 * Database access methods for the table "USER"
 */
public interface UserDAO extends BaseDAO<User> {

    /**
     * Retrieves the row with the given "username" from the table "USER"
     * @param username the value of the "USERNAME" column in the row
     * @return the obj representation of the retrieved row
     */
    User getUserByUsername(String username);


    /**
     * Checks if the row with the given "username" exists
     * @param username the value of "username" in the row
     * @return TRUE if the row exists, FALSE if not
     */
    boolean checkUserByUsername(String username);
}

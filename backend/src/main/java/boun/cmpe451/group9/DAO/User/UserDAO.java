package boun.cmpe451.group9.DAO.User;

import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

/**
 * Database access methods for the table "USER"
 */
public interface UserDAO {

    /**
     * Adds a new row in the table "USER"
     * @param user the obj representation of the added row
     */
    void addUser(User user);

    /**
     * Retrieves the row with the given id from the table "USER"
     * @param id the id of the row
     * @return the obj representation of the retrieved row
     */
    User getUserById(long id);

    /**
     * Retrieves the row with the given "username" from the table "USER"
     * @param username the value of the "USERNAME" column in the row
     * @return the obj representation of the retrieved row
     */
    User getUserByUsername(String username);

    /**
     * Updates the row in the table "USER"
     * @param user the obj representation of the updated row
     */
    void updateUser(User user);

    /**
     * Removes the row with the given id from the table "USER"
     * @param id the id of the removed row
     */
    void removeUser(long id);

    /**
     * Retrieves all rows from the table "USER"
     * @return the list of all rows from the table "USER"
     */
    List<User> getAllUsers();

    /**
     * Checks if the row with the given id exists in the table "USER"
     * @param id the id of the row
     * @return TRUE if the row exists, FALSE if not
     */
    boolean checkUserExists(long id);

    /**
     * Checks if the row with the given "username" exists
     * @param username the value of "username" in the row
     * @return TRUE if the row exists, FALSE if not
     */
    boolean checkUserByUsername(String username);
}

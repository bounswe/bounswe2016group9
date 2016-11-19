package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.BaseService;

/**
 * Service methods for the resource "User"
 */
@SuppressWarnings("unused")
public interface UserService extends BaseService<User> {

    /**
     * Retrieves the resource "User" with the given username
     * @param username the username of the user
     * @return the retrieved resource
     */
    User getUserByUsername(String username);

    /**
     * Checks if the user with the given "username" exists
     * @param username the username of the user
     * @return TRUE if the user exits, FALSE if not
     */
    boolean checkUserExistsByUsername(String username);
}
